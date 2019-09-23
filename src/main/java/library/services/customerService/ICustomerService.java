package library.services.customerService;

import library.models.customer.Customer;
import library.models.main.Author;

import java.time.LocalDate;
import java.util.Set;

public interface ICustomerService {
  void takeBook(String name, String title, Set<Author> authors, LocalDate date);
  void returnBook(String name, String title, Set<Author> authors, LocalDate date);
  void addCustomer(Customer customer);
  Customer findByName(String name);
}
