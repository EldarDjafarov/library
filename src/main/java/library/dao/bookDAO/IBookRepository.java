package library.dao.bookDAO;

import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface IBookRepository extends JpaRepository<Book,Long> {
    Book findByTitleAndAuthors(String title,Set<Author> authors);

    @Query("select distinct u from Book u left join fetch u.genres" +
            " left join fetch u.authors where u.id = :id or u.title = :title or u.date = :date or " +
            " u.genres = :genres") /*or u.authors = :authors or u.numberOfBooks = :numberOfBooks")*/
    Set<Book> findByIdOrTitleOrDateOrGenres/*OrAuthorsOrNumberOfBooks*/ (@Param("id") Long id, @Param("title") String title,
                            @Param("date")LocalDate date, @Param("genres") Set<Genre> genres/*,
                            @Param("authors") Set<Author> authors, @Param("numberOfBooks") Long numberOfBooks*/);
}




