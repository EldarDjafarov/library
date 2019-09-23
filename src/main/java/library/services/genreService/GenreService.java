package library.services.genreService;

import library.dao.genreDAO.IGenreRepository;
import library.models.main.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GenreService implements IGenreService {

    @Autowired
    private IGenreRepository genreRepository;
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public void addGenre(Genre genre) {
     genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);

    }

    @Override
    public Genre addGenreByName(String genreName) {
        if (genreRepository.findByName(genreName) == null) {
            Genre genre = new Genre();
            genre.setName(genreName);
            genreRepository.save(genre);
            return genreRepository.findByName(genreName);
        }else{
            return genreRepository.findByName(genreName);
        }
    }

    @Override
    public Set<Genre> addGenresByNames(String genresNames) {

        Set<Genre> genres=new HashSet<>();
        String[] genresNameArray=  genresNames.split(",");
        List<String> genresNamesList= Arrays.asList(genresNameArray);
        for(String genreName:genresNamesList){
            Genre genre= addGenreByName(genreName);
            genres.add(genre);

        }
        return genres;
    }


}


