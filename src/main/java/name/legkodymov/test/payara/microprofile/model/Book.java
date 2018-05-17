package name.legkodymov.test.payara.microprofile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Book {

    @Id
    private String isbn;

    private String author;

    private String title;
}
