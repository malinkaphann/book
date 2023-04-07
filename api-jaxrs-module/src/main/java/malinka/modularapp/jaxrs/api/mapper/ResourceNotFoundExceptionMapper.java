package malinka.modularapp.jaxrs.api.mapper;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.common.exception.ResourceNotFoundException;
import malinka.modularapp.common.util.STATUS;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This is to handle the resource not found exception.
 *
 * @author Malinka Phann
 */
public class ResourceNotFoundExceptionMapper implements
        ExceptionMapper<ResourceNotFoundException> {

    /**
     * @param e a thrown exception
     * @return Response a response
     */
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(ApiResponse
                        .builder()
                        .status(STATUS.ERROR_NOT_FOUND.getValue())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
