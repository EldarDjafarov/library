package library.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import library.models.customer.Customer;

import library.models.main.Author;
import library.models.main.Book;
import library.models.main.Genre;
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
@Api(value="Customer Management System", description="Operations related to the receipt and return of books and filtering them")
@Controller
public class CustomerController {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAuthorService authorService;

    @ApiOperation(value = "Return current date", response = LocalDate.class)
    @ModelAttribute
    LocalDate myDate() {
        return LocalDate.now();
    }
    @ApiOperation(value = "View list of Books")
    @GetMapping("/")
    public String listOfAllBooks(ModelMap modelMap) {
        List<Book> books = bookService.getAllBooks();
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("book",new Book());
        modelMap.addAttribute("author",new Author());
        modelMap.addAttribute("genre",new Genre());
        modelMap.addAttribute("customer",new Customer());
        return "customer";
    }
    @ApiOperation(value = "Take book")
    @PostMapping("take")
    public String takeBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
                           String authors) {
        customerService.takeBook(name, title, authorService.addAuthorsByNames(authors), currentDate);
        return "redirect:/";
    }
    @ApiOperation(value = "Return book")
    @PostMapping("return")
    public String returnBook(String name, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
                             String authors) {
        customerService.returnBook(name, title, authorService.addAuthorsByNames(authors), currentDate);
        return "redirect:/";
    }
    @ApiOperation(value = "Filter books")
    @PostMapping("filter")
    public String filterAll(Long id, String title, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate date
            , String genres, String authors, Long numberOfBooks, ModelMap modelMap) {

        Set<Book> books = bookService.filter(id, title, date, genres,
                authors, numberOfBooks);

        modelMap.addAttribute("books", books);
        modelMap.addAttribute("author",new Author());
        modelMap.addAttribute("genre",new Genre());
        return "filter";
    }
    @ApiOperation(value = "Filter Books")
    @GetMapping("filter")
    public String filterList() {
        return "filter";
    }
}

