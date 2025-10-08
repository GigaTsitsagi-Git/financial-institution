package org.example;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.*;

/**
 * Hello world!
 *
 */
public class App {
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
        System.out.println("Currency: " + usd);
        System.out.println("Symbol: " + usd.getSymbol());
        System.out.println("format: " + usd.formatAmount(new BigDecimal("1000")));
        System.out.println();

        AccountType savings = AccountType.SAVINGS;
        System.out.println("Account type: " + savings);
        System.out.println("Daily interest rate on 1000 = " + savings.calculateInterest(1000));
        System.out.println();

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

        //Creating Currency
        Currency currency = new Currency("USD", "$");
        bank.addCurrency(currency);

        //uncomment to throw exception
        //bank.exchangeMoney("GEL");

        // Creating models.CheckingAccount
        CheckingAccount checkingAccount = new CheckingAccount("ACC003", new BigDecimal("2000"), new BigDecimal("250"));

        //Creating models.SavingsAccount
        SavingsAccount savingsAccount = new SavingsAccount("ACC004", new BigDecimal("1000"), new BigDecimal("12.5"));

        try {
            checkingAccount.withdraw(new BigDecimal("2200"));
        } catch (InsufficientFundsException e) {
            System.out.println("Failed" + e.getMessage());
        } finally {
            System.out.println("Withdraw operation has ended");
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
        System.out.println("Net worth of bog is: " + netWorthBog);

        //Creating reportGenerator
        ReportGenerator reportGenerator = new ReportGenerator();

        //before everything
        reportGenerator.genererateCustomerReport(customerBob);

        // making transaction.Transaction
        Transaction transaction = new Transaction(accountAlice, accountBob, new BigDecimal("100.0"), "test");
        bank.addTransaction(transaction);

        //CheckingAcc limit amount = 750, overdraft limit = 250. expected 1000.
        boolean overdraftActive = checkingAccount.isOverdraftInUse();
        System.out.println("Is overdraft in use: " + overdraftActive);

        //SavingsAcc amount after year
        BigDecimal amountAfterYear = savingsAccount.calculateTotalValue();
        System.out.println("Total amount after year of saving will be: " + amountAfterYear);

        //after everything
        reportGenerator.genererateCustomerReport(customerBob);

        System.out.println("Alice Accounts");
        customerAlice.printAccounts();

        //Inheritance
        System.out.println("------- Inheritance output ----------");
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

        System.out.println();
        System.out.println();

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
        System.out.println(customerNotifier);

        Notifier<Account> accountNotifier = new Notifier<>(checkingAccount, "Good balance");
        System.out.println(accountNotifier);

        List<Customer> customers = List.of(
                new Customer("nika", "C007", 25),
                new Customer("giga", "C008", 21),
                new Customer("salome", "C009", 17)
        );

        Predicate<Customer> isAdult = c -> c.getAge() >= 18;
        customers.forEach(c -> System.out.println(c.getName() + " adult? " + isAdult.test(c)));

        Function<Customer, String> getName = Customer::getName;
        customers.forEach(c -> System.out.println("Name: " + getName.apply(c)));

        Consumer<Customer> printCustomer = c -> System.out.println("Customer: " + c);
        customers.forEach(printCustomer);

        Supplier<BigDecimal> defaultBalance = () -> BigDecimal.valueOf(1000);
        System.out.println("Default balance: " + defaultBalance.get());

        UnaryOperator<BigDecimal> addInterest = bal -> bal.multiply(BigDecimal.valueOf(1.05));
        System.out.println("Balance after interest: " + addInterest.apply(new BigDecimal("1000")));

        IAccountOperation deposit = (balance, amount) -> balance.add(amount);
        IAccountOperation withdraw = (balance, amount) ->
                balance.compareTo(amount) >= 0 ? balance.subtract(amount) : balance;

        BigDecimal balance = new BigDecimal("1000");
        balance = deposit.apply(balance, new BigDecimal("200"));
        balance = withdraw.apply(balance, new BigDecimal("500"));

        ICustomerNotifier emailNotifier = (customer, msg) ->
                System.out.println("Email to " + customer.getName() + ": " + msg);

        Customer c = new Customer("C001", "Alice", 25);
        emailNotifier.notify(c, "Your account balance is below minimum!");

        ILoanEvaluator simpleEvaluator = (customer, amount) ->
                customer.getAge() >= 18 && amount.compareTo(new BigDecimal("5000")) <= 0;

        Customer c1 = new Customer("C011", "Zura", 25);
        Customer c2 = new Customer("C012", "akaki", 17);

        System.out.println("Alice loan approved? " + simpleEvaluator.approve(c1, new BigDecimal("3000")));
        System.out.println("Bob loan approved? " + simpleEvaluator.approve(c2, new BigDecimal("3000")));

        System.out.println();


        TransactionStatus status = TransactionStatus.PENDING;
        System.out.println("Status: " + status);
        System.out.println("Description: " + status.getDescription());
        System.out.println("Is final? " + status.isFinalStatus());

        status = TransactionStatus.COMPLETED;
        System.out.println("Updated status: " + status);
        System.out.println("Is final? " + status.isFinalStatus());
        System.out.println();

        double baseRate = 0.05; // 5% base interest
        System.out.println("Risk: " + risk);
        System.out.println("Description: " + risk.getDescription());
        System.out.println("Penalty rate: " + risk.getPenaltyRate());
        System.out.println("Adjusted loan rate: " + risk.adjustLoanInterest(baseRate));
        System.out.println();

        ExchangeRate usdToEur = new ExchangeRate("USD", "EUR", new BigDecimal("0.92"));

        System.out.println("From: " + usdToEur.fromCurrency());
        System.out.println("To: " + usdToEur.toCurrency());
        System.out.println("Rate: " + usdToEur.rate());

        BigDecimal result = usdToEur.convert(new BigDecimal("100"));
        System.out.println("100 " + usdToEur.fromCurrency() + " = " + result + " " + usdToEur.toCurrency());


        accountBob.executeOperation(new BigDecimal("500"),
                (newBalance, amount) -> newBalance.add(amount));

        accountBob.executeOperation(new BigDecimal("200"),
                (newBalance, amount) -> newBalance.subtract(amount));

        accountBob.executeOperation(BigDecimal.ZERO,
                (newBalance, amount) -> newBalance.multiply(BigDecimal.valueOf(1.05)));
    }
}
