package malinka.modularapp.jaxrs.api.mapper;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.common.exception.ValidationException;
import malinka.modularapp.common.util.STATUS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This is to handle the validation exception.
 *
 * @author Malinka Phann
 */
public class ValidationExceptionMapper implements
        ExceptionMapper<ValidationException> {

    /**
     * @param e a thrown exception
     * @return Response a response
     */
    @Override
    public Response toResponse(ValidationException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ApiResponse
                        .builder()
                        .status(STATUS.ERROR_VALIDATION.getValue())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
