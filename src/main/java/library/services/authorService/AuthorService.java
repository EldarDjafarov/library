package library.services.authorService;

import library.dao.authorDAO.IAuthorRepository;
import library.models.main.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author addAuthorByName(String authorName) {
        if (authorRepository.findByName(authorName) == null) {
            Author author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
            return authorRepository.findByName(authorName);
        }else{
           return authorRepository.findByName(authorName);
        }
    }

    @Override
    public Set<Author> addAuthorsByNames(String authorsNames) {
        Set<Author> authors=new HashSet<>();
        String[] authorsNamesArray=  authorsNames.split(",");
        List<String> authorsNamesList= Arrays.asList(authorsNamesArray);
        for(String authorName:authorsNamesList){
            Author author=addAuthorByName(authorName);
           authors.add(author);

    }
        return authors;
}
}
