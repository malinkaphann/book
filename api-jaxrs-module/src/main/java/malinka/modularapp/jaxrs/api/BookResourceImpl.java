package malinka.modularapp.jaxrs.api;

import malinka.modularapp.api.dto.CreateBookDto;
import malinka.modularapp.api.dto.UpdateBookDto;
import malinka.modularapp.api.resource.BookResource;
import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.api.respone.DataApiResponse;
import malinka.modularapp.entity.Book;
import malinka.modularapp.service.BookService;
import javax.ws.rs.*;
import java.util.List;

/**
 * This is the implementation of the book resource interface
 * using the JAX-RS.
 *
 * @author Malinka Phann
 */
@Path("/book")
public class BookResourceImpl implements BookResource {

    private final BookService  bookService;

    public BookResourceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Fetch the detail of the book.
     *
     * @param id an id of the book
     * @return DataApiResponse a data-api response
     */
    @GET
    @Path("/detail/{id}")
    @Produces("application/json")
    @Override
    public DataApiResponse<Book> detail(final @PathParam("id") Long id) {
        return DataApiResponse
                .<Book>dataBuilder()
                .message(String.format("book id = %s is fetched successfully", id))
                .data(bookService.findById(id))
                .build();
    }

    /**
     * Fetch all books.
     *
     * @return DataApiResponse a data-api response
     */
    @GET
    @Path("/list")
    @Produces("application/json")
    @Override
    public DataApiResponse<List<Book>> list() {
        return DataApiResponse
                .<List<Book>>dataBuilder()
                .message("all books are fetched successfully")
                .data(bookService.findAll())
                .build();
    }
    /**
     * Create a new book.
     *
     * @param createBookDto a create-book dto
     * @return DataApiResponse a data-api response
     */
    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public DataApiResponse<Book> create(final CreateBookDto createBookDto) {
        Book book = bookService.create(Book.builder()
                .title(createBookDto.getTitle()).build());
        return DataApiResponse
                .<Book>dataBuilder()
                .message(String.format("book title = %s is inserted successfully",
                        createBookDto.getTitle()))
                .data(book)
                .build();
    }

    /**
     * Update the existing book.
     *
     * @param updateBookDto an update-book dto
     * @return DataApiResponse a data-api response
     */
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public DataApiResponse<Book> update(final @PathParam("id") Long id,
                                        final UpdateBookDto updateBookDto) {
        Book book = bookService.update(id, Book.builder()
                .title(updateBookDto.getTitle()).build());
        return DataApiResponse
                .<Book>dataBuilder()
                .message(String.format("book id = %d is updated successfully",
                        id))
                .data(book)
                .build();
    }

    /**
     * Remove a book.
     *
     * @param id an id of a book
     * @return ApiResponse an api response
     */
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    @Override
    public ApiResponse remove(final @PathParam("id") Long id) {
        bookService.remove(id);
        return ApiResponse
                .builder()
                .message(String.format("book id = %d is deleted successfully", id))
                .build();
    }
}
