package name.legkodymov.test.payara.microprofile.model;

import lombok.Data;

@Data
public class BookId {
    private final String isbn;
    private final String author;
}
