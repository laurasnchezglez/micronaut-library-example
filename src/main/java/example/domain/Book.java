package example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
* Aquesta classe de domini té el plugin lombok per els getters/setter. Si us plau, installa aquest plugin
* al teu intellij per a que no surtin errors.
* */
@Entity
@Table(name = "book")
@Getter //annotation de lombok
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ManyToOne
    private Genre genre;

    public Book() {}

    public Book(@NotNull String name, @NotNull String isbn, Genre genre) {
        this.isbn = isbn;
        this.name = name;
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                '}';
    }
}