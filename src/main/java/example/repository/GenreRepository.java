package example.repository;

import example.domain.Genre;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;



@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByName(@javax.validation.constraints.NotNull String name);
}