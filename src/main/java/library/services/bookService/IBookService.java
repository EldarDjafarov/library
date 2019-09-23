package library.services.bookService;

import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IBookService {
void addBook(Book book);
List<Book> getAllBooks();
Set<Book> filter(Long id, String title,
                 LocalDate date, Set<Genre> genres/*,
                 Set<Author> authors, Long numberOfBooks*/);
}
