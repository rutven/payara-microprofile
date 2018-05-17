package name.legkodymov.test.payara.microprofile.service;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@LoginConfig(authMethod = "MP-JWT", realmName = "admin-realm")
@ApplicationScoped
@ApplicationPath("/")
public class BookServiceConfig extends Application {
}
