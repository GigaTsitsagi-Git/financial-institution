package com.solvd.financialinstituion.interfaces.functional;

import com.solvd.financialinstituion.model.Customer;

import java.math.BigDecimal;

public interface ILoanEvaluator {

    boolean approve(Customer customer, BigDecimal amount);
}
