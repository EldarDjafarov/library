package library.models.main;


import library.models.customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_genre",
            joinColumns = {
                    @JoinColumn(name = "book_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id",
                            referencedColumnName = "id")})
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_author",
            joinColumns = {
                    @JoinColumn(name = "book_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id",
                            referencedColumnName = "id")})
    private Set<Author> authors;

    private Long numberOfBooks;
    @ManyToMany(mappedBy="books")
    private Set<Customer> customers;


    public Set<Customer> getCustomer() {
        return customers;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customers = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Long getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Long numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public Book() {
    }
}
