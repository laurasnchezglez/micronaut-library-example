package hello.world;

import example.domain.Book;
import example.repository.BookRepository;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoorRepositoryTest {

    @Inject
    BookRepository bookRepository;

    @BeforeAll
    public  void setUp(){
        Book newBook = new Book();
        newBook.setName("test");
        newBook.setIsbn("123");
        bookRepository.save(newBook);

    }

    @Test
    void testSaveBook() {

        Assertions.assertTrue(
                bookRepository.count() > 0
        );
    }

}
