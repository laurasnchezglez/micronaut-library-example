package example;

import example.domain.Book;
import example.domain.Genre;
import example.repository.BookRepository;
import example.repository.GenreRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.scheduling.annotation.Async;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;


/*
* Aquesta class es nomes per insertar dades a la H2 a l'inici
* */
@Singleton
@Requires(notEnv = Environment.TEST) // Don't load data in tests.
public class DataLoader implements ApplicationEventListener<ServiceStartedEvent> {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    /**
     * Reactive repository for Mongo database to store
     * Conference objects with an id and name property.
     */
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public DataLoader(final GenreRepository genreRepository, final BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }
    @Async
    @Override
    public void onApplicationEvent(final ServiceStartedEvent event) {
        log.info("Loading data at startup");
        // Transform names to Conferences object and save them.
        Flowable.just("Fantasy", "Science fiction", "Horror", "Mystery", "Romance")
                .map(name -> new Genre(name))
                .forEach(this::saveGenre);


        saveBook(new Book ("The Lord of the Rings", "123", genreRepository.findByName("Fantasy").get()));
        saveBook(new Book ("Foundation", "123", genreRepository.findByName("Science fiction").get()));
        saveBook(new Book ("Weird Tales", "133", genreRepository.findByName("Horror").get()));

    }


    private void saveGenre(Genre genre) {
        genreRepository
                .save(genre);
    }

    private void saveBook(Book book) {
        bookRepository
                .save(book);
    }
}