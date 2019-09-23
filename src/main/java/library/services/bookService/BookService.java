package library.services.bookService;

import library.dao.bookDAO.IBookRepository;
import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;
    @Override
    public void addBook(Book book) {
        Book bookFromDb=bookRepository.findByTitleAndAuthors(book.getTitle(),book.getAuthors());
    if(bookFromDb==null){
        bookRepository.save(book);
    }
       else if((bookFromDb!=null)&& (book.getNumberOfBooks()==0)){
           bookFromDb.setNumberOfBooks(bookFromDb.getNumberOfBooks()+1);
            bookRepository.save(bookFromDb);
        } else if((bookFromDb!=null)&& (book.getNumberOfBooks()>0)){
            book.setId(bookFromDb.getId());
        bookRepository.save(book);

    }

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Set<Book> filter(Long id, String title, LocalDate date, Set<Genre> genres/*, Set<Author> authors, Long numberOfBooks*/) {
        return bookRepository.findByIdOrTitleOrDateOrGenres/*OrGenresOrAuthorsOrNumberOfBooks*/(id,title, date,genres /*genres, authors,numberOfBooks*/);
    }

}
