package library.dao.userDAO;

import library.models.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
