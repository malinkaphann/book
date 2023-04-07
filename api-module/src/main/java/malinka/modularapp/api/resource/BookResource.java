package malinka.modularapp.api.resource;

import malinka.modularapp.api.dto.CreateBookDto;
import malinka.modularapp.api.dto.UpdateBookDto;
import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.api.respone.DataApiResponse;
import malinka.modularapp.entity.Book;

import java.util.List;

/**
 * This is the interface of a book resource.
 * The implementation of this interface should be found
 * in different modules using different ways to get it done.
 *
 * @author Malinka Phann
 */
public interface BookResource {
    DataApiResponse<Book> detail(Long id);
    DataApiResponse<List<Book>> list();
    DataApiResponse<Book> create(CreateBookDto createBookDto);
    DataApiResponse<Book> update(Long id, UpdateBookDto updateBookDto);
    ApiResponse remove(Long id);
}
