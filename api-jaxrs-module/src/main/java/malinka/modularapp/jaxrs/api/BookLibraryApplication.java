package malinka.modularapp.jaxrs.api;

import malinka.modularapp.jaxrs.api.mapper.*;
import malinka.modularapp.service.BookService;
import malinka.modularapp.service.BookServiceFactory;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

/**
 * This is the main entry of the api.
 *
 * @author Malinka Phann
 */
public class BookLibraryApplication {

    public static final String BASE_URI = "http://localhost:8080/";

    public static Server startServer() {
        BookService bookService = BookServiceFactory.getService();
        final ResourceConfig config = new ResourceConfig();
        config.register(new BookResourceImpl(bookService));
        config.register(DatabaseExceptionMapper.class);
        config.register(ValidationExceptionMapper.class);
        config.register(ResourceNotFoundExceptionMapper.class);
        config.register(ResourceDuplicatedExceptionMapper.class);
        config.register(WebApplicationExceptionMapper.class);
        config.register(NullPointerExceptionMapper.class);
        config.register(IllegalArgumentExceptionMapper.class);
        config.register(NothingUpdatedExceptionMapper.class);
        return JettyHttpContainerFactory.createServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {
        try {
            final Server server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    // shutdown the api
                    server.stop();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
            System.out.println("Api started. Stop the Api using CTRL+C");
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
