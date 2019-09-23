package library.controllers;


import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;
import library.services.authorService.IAuthorService;
import library.services.bookService.BookService;
import library.services.genreService.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private BookService bookService;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IGenreService genreService;


    @GetMapping()
    public String listOfAllBooks(ModelMap modelMap) {
        List<Book> books = bookService.getAllBooks();
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("book",new Book());
        modelMap.addAttribute("author",new Author());
        modelMap.addAttribute("genre",new Genre());

        return "main";
    }
    @PostMapping()
    public String addBook(String title,  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
                          String authors, String genres,
                          @RequestParam(required = false,defaultValue="0")Long numberOfBooks) {
        Book book=new Book();
        book.setTitle(title);
        book.setDate(date);
        book.setAuthors(authorService.addAuthorsByNames(authors));
        book.setGenres(genreService.addGenresByNames(genres));
        book.setNumberOfBooks(numberOfBooks);
        bookService.addBook(book);
            return "redirect:/main";


    }
}
