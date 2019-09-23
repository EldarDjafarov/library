package library.dao.customerDAO;

import library.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
Customer findByName(String name);
}
