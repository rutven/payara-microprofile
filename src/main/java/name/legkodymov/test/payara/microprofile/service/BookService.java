package name.legkodymov.test.payara.microprofile.service;

import name.legkodymov.test.payara.microprofile.model.Book;
import name.legkodymov.test.payara.microprofile.model.Books;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    private EntityManager entityManager;

    @Inject
    @ConfigProperty(name = "max.books.per.page", defaultValue = "20")
    private int maxBooks;

    public Books getAll() {

        List<Book> bookList = entityManager
                .createQuery("select b from Book b", Book.class)
                .setMaxResults(maxBooks)
                .getResultList();

        return new Books(bookList);

    }
}
