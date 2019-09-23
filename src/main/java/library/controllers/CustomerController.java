package library.controllers;

import library.models.customer.Customer;

import library.models.main.Book;
import library.services.authorService.IAuthorService;
import library.services.bookService.IBookService;
import library.services.customerService.ICustomerService;
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
    private IBookService bookService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAuthorService authorService;


    @ModelAttribute
    LocalDate myDate() {
        return LocalDate.now();
    }

    @GetMapping()
    public String listOfAllBooks(ModelMap modelMap) {
        List<Book> books = bookService.getAllBooks();
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping("/take")
    public String takeBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
                           String authors) {
        customerService.takeBook(name, title, authorService.addAuthorsByNames(authors), currentDate);
        return "redirect:/";
    }

    @PostMapping("/return")
    public String returnBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
                             String authors) {
        customerService.returnBook(name, title, authorService.addAuthorsByNames(authors), currentDate);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterAll(Long id, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate date
            , String genre, String authors, Long numberOfBooks, ModelMap modelMap) {


        Set<Book> books = bookService.filter(id, title, date, genre,
                authors, numberOfBooks);

        modelMap.addAttribute("books", books);
        return "redirect:/";
    }
}

