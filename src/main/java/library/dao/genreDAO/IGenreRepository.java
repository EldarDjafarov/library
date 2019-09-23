package library.dao.genreDAO;

import library.models.main.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre,Long> {
    Genre findByName(String genreName);

}
