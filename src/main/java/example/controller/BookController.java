package example.controller;

import com.sun.istack.NotNull;
import example.domain.Book;
import example.domain.Genre;
import example.repository.BookRepository;
import example.repository.GenreRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/*
* Controller de books
* */

//L'authenticació que aplica és la del provider implementat. En aquest cas, basicAuth
@Secured("isAuthenticated()")
@Controller("/books") // <1>
public class BookController {

    /*Micronaut pot injectar la dependencia dels repositoris en un constructor (com paràmetre) o fem servir
    * aquesta annotació @inject*/
    @Inject
    private BookRepository bookRepository;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Iterable<Book> index() {

        return bookRepository.findAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book get(@NotNull Long id) {
        return bookRepository.findById(id).get();
    }

    @Get("/genre/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book get(@NotNull String name) {
        return bookRepository.findByGenreName(name).get();
    }

    /*
    * En aquest cas, s'injecta el nou book com un json al requet body del PUT
    * */
    @Put
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book put(@NotNull @Body Book book) {
        return bookRepository.save(book);
    }

}