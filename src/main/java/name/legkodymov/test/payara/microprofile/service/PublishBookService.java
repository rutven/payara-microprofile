package name.legkodymov.test.payara.microprofile.service;

import name.legkodymov.test.payara.microprofile.model.Author;
import name.legkodymov.test.payara.microprofile.model.Book;
import name.legkodymov.test.payara.microprofile.model.BookId;
import name.legkodymov.test.payara.microprofile.model.PublishBook;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotAuthorizedException;

@RequestScoped
public class PublishBookService {

    @Inject
    private JsonWebToken jsonWebToken;

    @Inject
    private AuthorService authorService;

    @Inject
    private EntityManager entityManager;

    @Timeout(500)
    public BookId publish(PublishBook publishBook) {

        if (!publishBook.getAuthor().equals(jsonWebToken.getSubject())) {
            boolean createAny = jsonWebToken.getClaim("create.books.for.other.authors");

            if (!createAny) {
                throw new NotAuthorizedException("Cannot create book, wrong author");
            }
        }

        Author author = authorService.findAuthor(publishBook.getAuthor());

        if (author == null) {
            throw new NotAuthorizedException("The list author is not an author");
        }

        Book book = entityManager.merge(new Book(publishBook.getIsbn(), publishBook.getAuthor(), publishBook.getTitle()));

        return new BookId(book.getIsbn(), book.getAuthor());
    }
}
