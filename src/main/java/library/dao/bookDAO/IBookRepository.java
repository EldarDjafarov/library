package library.dao.bookDAO;

import library.models.main.Author;
import library.models.main.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.Set;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthors(String title, Set<Author> authors);

    @Query("select  distinct u from Book u left join fetch u.genres g " +
            " left join fetch u.authors aut where u.id=?1 or u.title=?2 or u.date=?3 or g.name=?4 or aut.name=?5 or u.numberOfBooks=?6")
    Set<Book> findByIdOrTitleOrDateOrGenresOrAuthorsOrNumberOfBooks(Long id,String title,
                                                           LocalDate date, String genre,
                                                           String author, Long numberOfBooks);


    }