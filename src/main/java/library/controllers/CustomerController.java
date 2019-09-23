package library.controllers;

import library.models.customer.Customer;
import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;
import library.services.authorService.IAuthorService;
import library.services.bookService.BookService;
import library.services.customerService.ICustomerService;
import library.services.genreService.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IGenreService genreService;


    @ModelAttribute
    LocalDate myDate() {
        return LocalDate.now();
    }

    @GetMapping()
    public String listOfAllBooks(ModelMap modelMap) {
        List<Book> books = bookService.getAllBooks();
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("customer", new Customer());
        modelMap.addAttribute("genre", new Genre());
        modelMap.addAttribute("author",new Author());
        return "customer";
    }

    @PostMapping("/take")
    public String takeBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate currentDate,
                           String authors) {
       customerService.takeBook(name,title,authorService.addAuthorsByNames(authors),currentDate);
        return "redirect:/";
    }
    @PostMapping("/return")
    public String returnBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate currentDate,
                           String authors) {
        customerService.returnBook(name,title,authorService.addAuthorsByNames(authors),currentDate);
        return "redirect:/";
    }
    @PostMapping("/filter")
    public String filterAll(Long id, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate date
                         ,@RequestParam(required = false,defaultValue="none") String genres /*String authors,Long numberOfBooks*/) {

       bookService.filter(id, title,date, genreService.addGenresByNames(genres)
                /*authorService.addAuthorsByNames(authors),numberOfBooks*/);


        return "redirect:/";
    }
}

