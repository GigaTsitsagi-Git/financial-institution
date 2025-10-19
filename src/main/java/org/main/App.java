package org.main;

import exception.InsufficientFundsException;
import autocloseable.DatabaseConnection;
import document.Contract;
import document.Document;
import document.Receipt;
import employee.BankEmployee;
import employee.Employee;
import employee.Manager;
import employee.Person;
import interfaces.IPrint;
import interfaces.functional.IAccountOperation;
import interfaces.functional.ICustomerNotifier;
import interfaces.functional.ILoanEvaluator;
import model.*;
import record.ExchangeRate;
import service.AccountPrinter;
import service.Notifier;
import service.ReportGenerator;
import trading.Stock;
import trading.Trader;
import transaction.FinancialExchange;
import transaction.Loan;
import transaction.Transaction;
import enums.*;
import concurrent.ConnectionPool;
import concurrent.AccountDb;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.Arrays;
import java.util.stream.Collectors;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        FinancialInstitution financialInstitution = new FinancialInstitution("Institution Name", "Tbilisi");

        //Creating banks
        Bank bank = new Bank("Bank of Georgia");
        financialInstitution.addBank(bank);
        //Creating FinancialExchange
        FinancialExchange financialExchange = new FinancialExchange("Exchange name");
        financialInstitution.addFinancialExchange(financialExchange);

        // Create customers
        Customer customerAlice = new Customer("Alice", "C001", 21);
        Customer ExceptionCustomer = new Customer("Alice", "C001", 16);
        Customer customerBob = new Customer("Bob", "C002", 30);

        bank.addCustomer(customerBob);
        bank.addCustomer(customerAlice);
        //bank.addCustomer(ExceptionCustomer);

        CurrencyType usd = CurrencyType.USD;
        logger.info("Currency: {}", usd);
        logger.info("Symbol: {}", usd.getSymbol());
        logger.info("Format 1000: {}", usd.formatAmount(new BigDecimal("1000")));
        logger.info("");

        AccountType savings = AccountType.SAVINGS;
        logger.info("Account type: {}", savings);
        logger.info("Daily interest rate on 1000 = {}", savings.calculateInterest(1000));
        logger.info("");

        // Create accounts
        Account accountAlice = new Account("ACC001", savings, usd, new BigDecimal("1000.0"));
        Account accountBob = new Account("ACC002", savings, usd, new BigDecimal("500.0"));
        //Account exceptionAcc = new Account("", new BigDecimal("1000.0"));

        // Creating Credit card
        CreditCard creditCard = new CreditCard("C000111222", LocalDate.now(), "123");
        customerBob.addCreditCard(creditCard);

        RiskLevel risk = RiskLevel.HIGH;
        LoanType loanType = LoanType.PERSONAL;
        //Creating Loan
        Loan loan = new Loan(customerAlice, new BigDecimal("500"), new BigDecimal("12.5"), loanType, risk);
        customerAlice.addLoan(loan);

        //Creating Currency using both approaches
        Currency currency = new Currency("USD", "$");
        bank.addCurrency(currency);

        // Adding currencies using enum
        bank.addCurrency(CurrencyType.EUR);
        bank.addCurrency(CurrencyType.GBP);
        bank.addCurrency(CurrencyType.GEL);

        //uncomment to throw exception
        //bank.exchangeMoney("GEL");

        // Creating models.CheckingAccount
        CheckingAccount checkingAccount = new CheckingAccount("ACC003", new BigDecimal("2000"), new BigDecimal("250"));

        //Creating models.SavingsAccount
        SavingsAccount savingsAccount = new SavingsAccount("ACC004", new BigDecimal("1000"), new BigDecimal("12.5"));

        try {
            checkingAccount.withdraw(new BigDecimal("2200"));
        } catch (InsufficientFundsException e) {
            logger.error("Failed: {}", e.getMessage());
        } finally {
            logger.info("Withdraw operation has ended");
        }
        // Link accounts to customers
        customerAlice.addAccount(accountAlice);
        customerBob.addAccount(accountBob);

        //Creating trading.Trader
        Trader trader = new Trader("0", customerBob);
        financialExchange.addTrader(trader);
        trader.showCustomerAccounts();

        //Creating trading.Stock
        Stock stockBog = new Stock("BOG", 100000, new BigDecimal("1200"));
        financialExchange.addStock(stockBog);
        BigDecimal netWorthBog = stockBog.getNetWorth();
        logger.info("Net worth of BOG is: {}", netWorthBog);

        //Creating reportGenerator
        ReportGenerator reportGenerator = new ReportGenerator();

        //before everything
        reportGenerator.genererateCustomerReport(customerBob);

        // making transaction.Transaction
        Transaction transaction = new Transaction(accountAlice, accountBob, new BigDecimal("100.0"), "test");
        bank.addTransaction(transaction);

        // Demonstrate enhanced transaction status methods
        logger.info("Transaction status: {}", transaction.getTransactionStatus());
        logger.info("Transaction info: {}", transaction.getTransactionInfo());
        transaction.completeTransaction();
        logger.info("After completion: {}", transaction.getTransactionInfo());

        // Demonstrate bank enum integration
        logger.info("Bank supports USD: {}", bank.supportsCurrency(CurrencyType.USD));
        logger.info("Bank supports EUR: {}", bank.supportsCurrency(CurrencyType.EUR));
        logger.info("Pending transactions: {}", bank.getPendingTransactions().size());
        logger.info("Completed transactions: {}", bank.getCompletedTransactions().size());
        logger.info("Bank info: {}", bank.getBankInfo());

        //CheckingAcc limit amount = 750, overdraft limit = 250. expected 1000.
        boolean overdraftActive = checkingAccount.isOverdraftInUse();
        logger.info("Is overdraft in use: {}", overdraftActive);

        //SavingsAcc amount after year
        BigDecimal amountAfterYear = savingsAccount.calculateTotalValue();
        logger.info("Total amount after year of saving will be: {}", amountAfterYear);

        //after everything
        reportGenerator.genererateCustomerReport(customerBob);

        logger.info("Alice Accounts:");
        customerAlice.printAccounts();

        // Demonstrate enhanced account enum integration
        logger.info("Account Alice info: {}", accountAlice.getAccountInfo());
        logger.info("Formatted balance: {}", accountAlice.getFormattedBalance());
        logger.info("Interest for account type: {}", accountAlice.calculateInterestForType());

        logger.info("Account Bob info: {}", accountBob.getAccountInfo());
        logger.info("Formatted balance: {}", accountBob.getFormattedBalance());

        //Inheritance
        logger.info("------- Inheritance output ----------");
        Employee emp1 = new BankEmployee("George", "Cooper", 28, "E001", new BigDecimal("2000"));
        Employee emp2 = new Manager("Nika", "smith", 40, "E002", new BigDecimal("5000"));

        //throws exception
        //((BankEmployee) emp1).approveLoan(new BigDecimal("6000"));
        ((BankEmployee) emp2).approveLoan(new BigDecimal("6000"));
        bank.addEmployee(emp1);
        bank.addEmployee(emp2);

        emp1.work();
        emp2.work();

        customerBob.move();

        Document document1 = new Contract("001", LocalDate.now(), "Alpha", "Beta");
        Document document2 = new Receipt("002", LocalDate.now(), "Groceries", new BigDecimal("100"));

        logger.info("");
        logger.info("");

        //Interface methods
        document1.printDetails();
        document2.printDetails();

        customerBob.move();
        customerAlice.save();

        accountBob.save();

        document1.getCreatedTime();
        document2.getCreatedTime();

        loan.printMoneyTransferred();
        transaction.printMoneyTransferred();

        // Demonstrate enhanced loan enum integration
        logger.info("Loan info: {}", loan.getLoanInfo());
        logger.info("Total interest for 2 years: {}", loan.calculateTotalInterest(2));
        logger.info("Adjusted interest rate: {}", loan.getAdjustedInterestRate());
        logger.info("Is high risk loan: {}", loan.isHighRiskLoan());
        logger.info("Risk penalty: {}", loan.getRiskPenalty());

        IPrint person = new Person("Giga", "Tsitsagi", 21);
        IPrint bankEmployee = new BankEmployee("salome", "smthng", 21, "E003", new BigDecimal("3000"));
        IPrint manager = new Manager("rati", "smthng1", 21, "E004", new BigDecimal("5000"));

        reportGenerator.printDetails(person);
        reportGenerator.printDetails(bankEmployee);
        reportGenerator.printDetails(manager);

        try (DatabaseConnection db = new DatabaseConnection("BankDb")) {
            db.executeQuery("SELECT * FROM Accounts");
            db.executeQuery("SELECT * FROM Customers");
        }

        AccountPrinter<Account> accountPrinter = new AccountPrinter<>(accountAlice);
        AccountPrinter<SavingsAccount> savingsAccountPrinter = new AccountPrinter<>(savingsAccount);
        accountPrinter.printAccount();
        savingsAccountPrinter.printAccount();

        Notifier<Customer> customerNotifier = new Notifier<>(customerBob, "Hello");
        logger.info(customerNotifier);

        Notifier<Account> accountNotifier = new Notifier<>(checkingAccount, "Good balance");
        logger.info(accountNotifier);

        List<Customer> customers = List.of(
                new Customer("nika", "C007", 25),
                new Customer("giga", "C008", 21),
                new Customer("salome", "C009", 17)
        );

        Predicate<Customer> isAdult = c -> c.getAge() >= 18;
        customers.forEach(c -> logger.info(c.getName() + " adult? " + isAdult.test(c)));

        Function<Customer, String> getName = Customer::getName;
        customers.forEach(c -> logger.info("Name: " + getName.apply(c)));

        Consumer<Customer> printCustomer = c -> logger.info("Customer: " + c);
        customers.forEach(printCustomer);

        Supplier<BigDecimal> defaultBalance = () -> BigDecimal.valueOf(1000);
        logger.info("Default balance: " + defaultBalance.get());

        UnaryOperator<BigDecimal> addInterest = bal -> bal.multiply(BigDecimal.valueOf(1.05));
        logger.info("Balance after interest: " + addInterest.apply(new BigDecimal("1000")));

        IAccountOperation deposit = (balance, amount) -> balance.add(amount);
        IAccountOperation withdraw = (balance, amount) ->
                balance.compareTo(amount) >= 0 ? balance.subtract(amount) : balance;

        BigDecimal balance = new BigDecimal("1000");
        balance = deposit.apply(balance, new BigDecimal("200"));
        balance = withdraw.apply(balance, new BigDecimal("500"));

        ICustomerNotifier emailNotifier = (customer, msg) ->
                logger.info("Email to {}: {}", customer.getName(), msg);

        Customer c = new Customer("C001", "Alice", 25);
        emailNotifier.notify(c, "Your account balance is below minimum!");

        ILoanEvaluator simpleEvaluator = (customer, amount) ->
                customer.getAge() >= 18 && amount.compareTo(new BigDecimal("5000")) <= 0;

        Customer c1 = new Customer("C011", "Zura", 25);
        Customer c2 = new Customer("C012", "akaki", 17);

        logger.info("Alice loan approved? {}", simpleEvaluator.approve(c1, new BigDecimal("3000")));
        logger.info("Bob loan approved? {}", simpleEvaluator.approve(c2, new BigDecimal("3000")));

        logger.info("");


        TransactionStatus status = TransactionStatus.PENDING;
        logger.info("Status: {}", status);
        logger.info("Description: {}", status.getDescription());
        logger.info("Is final? {}", status.isFinalStatus());

        status = TransactionStatus.COMPLETED;
        logger.info("Updated status: {}", status);
        logger.info("Is final? {}", status.isFinalStatus());
        logger.info("");

        double baseRate = 0.05; // 5% base interest
        logger.info("Risk: {}", risk);
        logger.info("Description: {}", risk.getDescription());
        logger.info("Penalty rate: {}", risk.getPenaltyRate());
        logger.info("Adjusted loan rate: {}", risk.adjustLoanInterest(baseRate));
        logger.info("");

        ExchangeRate usdToEur = new ExchangeRate("USD", "EUR", new BigDecimal("0.92"));

        logger.info("From: {}", usdToEur.fromCurrency());
        logger.info("To: {}", usdToEur.toCurrency());
        logger.info("Rate: {}", usdToEur.rate());

        BigDecimal result = usdToEur.convert(new BigDecimal("100"));
        logger.info("100 {} = {} {}", usdToEur.fromCurrency(), result, usdToEur.toCurrency());


        accountBob.executeOperation(new BigDecimal("500"),
                (newBalance, amount) -> newBalance.add(amount));

        accountBob.executeOperation(new BigDecimal("200"),
                (newBalance, amount) -> newBalance.subtract(amount));

        accountBob.executeOperation(BigDecimal.ZERO,
                (newBalance, amount) -> newBalance.multiply(BigDecimal.valueOf(1.05)));


        File input = new File("src/main/resources/book.txt");
        File output = new File("src/main/resources/result.txt");

        String text = FileUtils.readFileToString(input, StandardCharsets.UTF_8);

        String[] words = StringUtils.split(text.toLowerCase(), " ,.!?;:\"()[]\n\r\t");

        Set<String> uniqueWords = Arrays.stream(words)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());

        String bookResult = STR."""
                    Total words: \{words.length}
                    Unique words: \{uniqueWords.size()}""";

        FileUtils.writeStringToFile(output, bookResult, StandardCharsets.UTF_8);

        logger.info("Done! Results saved in {}", output.getName());

        // Thread Demo
        logger.info("Creating 2 threads");
        Thread thread1 = new Thread(() -> {
            logger.info("Thread 1 running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread 1 interrupted");
            }
            logger.info("Thread 1 done");
        });

        Thread thread2 = new Thread(() -> {
            logger.info("Thread 2 running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread 2 interrupted");
            }
            logger.info("Thread 2 done");
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            logger.error("Main interrupted");
        }

        // Connection Pool Demo
        logger.info("Creating connection pool");
        ConnectionPool pool = ConnectionPool.getInstance(5);

        // Create 7 threads with pool
        Thread[] threads = new Thread[7];
        for (int i = 0; i < 7; i++) {
            final int id = i + 1;
            threads[i] = new Thread(() -> {
                try {
                    AccountDb conn = pool.getConnection();
                    logger.info("Thread {} got connection {}", id, conn.getConnectionId());
                    Thread.sleep(1000);
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    logger.error("Thread {} interrupted", id);
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.error("Interrupted");
            }
        }

        // ThreadPool Demo
        logger.info("Creating ThreadPool");
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            final int id = i + 1;
            threadPool.submit(() -> {
                try {
                    AccountDb conn = pool.getConnection();
                    logger.info("Pool task {} got connection {}", id, conn.getConnectionId());
                    Thread.sleep(1000);
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    logger.error("Pool task {} interrupted", id);
                }
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Pool shutdown interrupted");
        }

        // ExecutorService Demo
        logger.info("Creating ExecutorService");
        ExecutorService executor = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            final int id = i + 1;
            executor.submit(() -> {
                try {
                    AccountDb conn = pool.getConnection();
                    logger.info("Executor task {} got connection {}", id, conn.getConnectionId());
                    Thread.sleep(1000);
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    logger.error("Executor task {} interrupted", id);
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Executor shutdown interrupted");
        }
    }
}
