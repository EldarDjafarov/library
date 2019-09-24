package library.dao.authorDAO;

import library.models.main.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
    Author findByName(String fullName);



}
