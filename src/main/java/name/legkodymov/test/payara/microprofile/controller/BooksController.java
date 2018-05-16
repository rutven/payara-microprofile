package name.legkodymov.test.payara.microprofile.controller;

import name.legkodymov.test.payara.microprofile.model.Books;
import name.legkodymov.test.payara.microprofile.service.BookService;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/books")
@Counted
@RequestScoped
public class BooksController {

    @Inject
    private BookService bookService;

    @GET
    @RolesAllowed("read-books")
    public Books findAll() {
        return bookService.getAll();
    }
}
