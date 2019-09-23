package library.services.authorService;

import library.models.main.Author;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAuthorService {
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    void addAuthor(Author author);

    void deleteAuthor(Long id);

    Author addAuthorByName(String authorName);
    Set<Author> addAuthorsByNames(String authorsNames);
}
