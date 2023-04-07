package malinka.modularapp.jaxrs.api.mapper;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.common.util.STATUS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This is to handle the illegal argument exception.
 *
 * @author Malinka Phann
 */
public class IllegalArgumentExceptionMapper implements
        ExceptionMapper<IllegalArgumentException> {

    /**
     * @param e a thrown exception
     * @return Response a response
     */
    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ApiResponse
                        .builder()
                        .status(STATUS.FAIL.getValue())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
