package com.solvd.financialinstituion.interfaces.functional;

import com.solvd.financialinstituion.model.Customer;

@FunctionalInterface
public interface ICustomerNotifier {

    void notify(Customer customer, String message);
}
