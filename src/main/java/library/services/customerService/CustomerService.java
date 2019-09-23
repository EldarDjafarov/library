package library.services.customerService;

import library.dao.bookDAO.IBookRepository;
import library.dao.customerDAO.ICustomerRepository;
import library.models.customer.Customer;
import library.models.main.Author;
import library.models.main.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void takeBook(String name, String title, Set<Author> authors, LocalDate date) {
        Book bookFromDb = bookRepository.findByTitleAndAuthors(title, authors);
        Customer customer = customerRepository.findByName(name);
        Set<Book> newBooks = new HashSet<>();
        if (bookFromDb != null && bookFromDb.getNumberOfBooks() > 1) {
            bookFromDb.setNumberOfBooks(bookFromDb.getNumberOfBooks() - 1);
            bookRepository.save(bookFromDb);
            newBooks.add(bookFromDb);
        }
        if (customer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setBooks(newBooks);
            newCustomer.setName(name);
            newCustomer.setDate(date);
            customerRepository.save(newCustomer);

        }else{
           Set<Book> oldBooks= customer.getBooks();
            for (Book book:
               newBooks  ) {
                oldBooks.add(book);
            }
            customer.setBooks(oldBooks);
            customer.setDate(date);

            customerRepository.save(customer);
        }

    }

    @Override
    public void returnBook(String name, String title, Set<Author> authors, LocalDate date) {
        if (customerRepository.findByName(name) != null) {
            Book bookFromDb = bookRepository.findByTitleAndAuthors(title, authors);
            bookFromDb.setNumberOfBooks(bookFromDb.getNumberOfBooks() + 1);
            bookRepository.save(bookFromDb);
            customerRepository.delete(customerRepository.findByName(name));
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }
}