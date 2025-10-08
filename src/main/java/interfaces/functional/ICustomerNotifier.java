package interfaces.functional;

import model.Customer;

@FunctionalInterface
public interface ICustomerNotifier {

    void notify(Customer customer, String message);
}
