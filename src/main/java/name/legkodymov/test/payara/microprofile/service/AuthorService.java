package name.legkodymov.test.payara.microprofile.service;

import name.legkodymov.test.payara.microprofile.model.Author;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class AuthorService {

    @Inject
    @RestClient
    AuthorConnector authorConnector;

    private ConcurrentMap<String, Author> authorCache = new ConcurrentHashMap<>();

    @Retry
    @CircuitBreaker
    @Fallback(fallbackMethod = "getCachedAuthor")
    public Author findAuthor(String id) {

        Author author = authorConnector.get(id);

        authorCache.put(id, author);

        return author;
    }

    public Author getCachedAuthor(String id) {
        return authorCache.get(id);
    }
}
