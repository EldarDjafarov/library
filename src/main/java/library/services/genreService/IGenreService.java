package library.services.genreService;


import library.models.main.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IGenreService {
    List<Genre> getAllGenres();
    Optional<Genre> getGenreById(Long id);
    void addGenre(Genre genre);
    void deleteGenre(Long id);
    Genre addGenreByName(String genreName);
    Set<Genre> addGenresByNames(String genresNames);
}
