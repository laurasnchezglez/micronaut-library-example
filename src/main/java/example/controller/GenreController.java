package example.controller;

import com.sun.istack.NotNull;
import example.domain.Genre;
import example.repository.GenreRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;

import javax.inject.Inject;
import javax.transaction.Transactional;


@Secured("isAuthenticated()")
@Controller("/genres") // <1>
public class GenreController {

    @Inject
    private  GenreRepository genreRepository;

   // public GenreController(GenreRepository genrerepo){
      //  this.genreRepository = genrerepo;
    //}

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Iterable<Genre> index() {
        Iterable<Genre> all = genreRepository.findAll();

        return all;
    }

    @Get("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Genre get(@NotNull Long id) {
        return genreRepository.findById(id).get();
    }

}