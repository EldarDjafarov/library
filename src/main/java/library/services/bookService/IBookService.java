package library.services.bookService;

import library.models.main.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IBookService {
    void addBook(Book book);

    List<Book> getAllBooks();

    Set<Book> filter(Long id, String title,
                     LocalDate date,String genres,
                     String authors, Long numberOfBooks);


}