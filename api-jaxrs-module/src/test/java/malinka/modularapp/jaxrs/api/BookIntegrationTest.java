package malinka.modularapp.jaxrs.api;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.api.respone.DataApiResponse;
import malinka.modularapp.common.util.STATUS;
import malinka.modularapp.entity.Book;
import malinka.modularapp.jaxrs.api.mapper.*;
import malinka.modularapp.service.BookService;
import malinka.modularapp.service.BookServiceFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * This is the book integration test.
 * The test cases are executed in order.
 *
 * @author Malinka Phann
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookIntegrationTest extends JerseyTest {

    private final String LONG_TITLE = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Override
    protected Application configure() {
        BookService bookService = BookServiceFactory.getService();
        ResourceConfig config = new ResourceConfig();
        config.register(new BookResourceImpl(bookService));
        config.register(DatabaseExceptionMapper.class);
        config.register(ValidationExceptionMapper.class);
        config.register(ResourceNotFoundExceptionMapper.class);
        config.register(ResourceDuplicatedExceptionMapper.class);
        config.register(WebApplicationExceptionMapper.class);
        config.register(NullPointerExceptionMapper.class);
        config.register(IllegalArgumentExceptionMapper.class);
        config.register(NothingUpdatedExceptionMapper.class);
        return config;
    }

    @Test
    public void t001_givenCreateBookAndNoTitle_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/create").request()
                .post(Entity.json("{}"));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final ApiResponse data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t002_givenPostCreateBookAndEmptyTitle_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/create").request()
                .post(Entity.json("{\"title\": \"\"}"));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final ApiResponse data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t003_givenCreateBookAndTooLongTitle_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/create").request()
                .post(Entity.json(String.format("{\"title\": \"%s\"}", LONG_TITLE)));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final ApiResponse data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t004_givenCreateBook_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/create").request()
                .post(Entity.json("{\"title\": \"book1\"}"));
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.SUCCESS.getValue(), data.getStatus());
        assertEquals("Data: ", "book1", data.getData().getTitle());
        response.close();
    }

    @Test
    public void t005_givenGetBookList_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/list").request().get();
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<List<Book>> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.SUCCESS.getValue(), data.getStatus());
        assertEquals("Data Length:", 1, data.getData().size());
        response.close();
    }

    @Test
    public void t006_givenUpdateBookWithNegativeId_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/update/-1").request()
                .put(Entity.json("{\"title\": \"book2\"}"));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t007_givenUpdateBookWithNotFoundId_whenSendRequest_thenResponseIs404() {
        final Response response = target("/book/update/111").request()
                .put(Entity.json("{\"title\": \"book2\"}"));
        assertEquals("Http Status: ",404, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_NOT_FOUND.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t008_givenUpdateBookWithNoTitle_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/update/1").request()
                .put(Entity.json("{}"));
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_NOTHING_UPDATED.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t009_givenUpdateBookWithEmptyTitle_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/update/1").request()
                .put(Entity.json("{\"title\": \"\"}"));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t010_givenUpdateBookWithTooLongTitle_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/update/1").request()
                .put(Entity.json(String.format("{\"title\": \"%s\"}", LONG_TITLE)));
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t011_givenUpdateBook_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/update/1").request()
                .put(Entity.json(String.format("{\"title\": \"%s\"}", "book2")));
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.SUCCESS.getValue(), data.getStatus());
        assertEquals("Data: ", "book2", data.getData().getTitle());
        response.close();
    }

    @Test
    public void t012_givenGetBookDetailWithNegativeId_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/detail/-1").request().get();
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final ApiResponse data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t013_givenGetBookDetailWithNotFoundId_whenSendRequest_thenResponseIs404() {
        final Response response = target("/book/detail/111").request().get();
        assertEquals("Http Status: ",404, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final ApiResponse data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_NOT_FOUND.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t014_givenGetBookDetail_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/detail/1").request().get();
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.SUCCESS.getValue(), data.getStatus());
        // t011 already changes the title from "book1" to "book2"
        assertEquals("Data: ", "book2", data.getData().getTitle());
        response.close();
    }

    @Test
    public void t015_givenDeleteBookByNegativeId_whenSendRequest_thenResponseIs400() {
        final Response response = target("/book/delete/-1").request().delete();
        assertEquals("Http Status: ",400, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_VALIDATION.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t016_givenDeleteBookByNotFoundId_whenSendRequest_thenResponseIs404() {
        final Response response = target("/book/delete/111").request().delete();
        assertEquals("Http Status: ",404, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.ERROR_NOT_FOUND.getValue(), data.getStatus());
        response.close();
    }

    @Test
    public void t017_givenDeleteBook_whenSendRequest_thenResponseIs200() {
        final Response response = target("/book/delete/1").request().delete();
        assertEquals("Http Status: ",200, response.getStatus());
        assertEquals("Http Content-Type: ", MediaType.APPLICATION_JSON,
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        final DataApiResponse<Book> data = response.readEntity(new GenericType<>(){});
        assertEquals("Status Code: ", STATUS.SUCCESS.getValue(), data.getStatus());
        response.close();
    }
}

