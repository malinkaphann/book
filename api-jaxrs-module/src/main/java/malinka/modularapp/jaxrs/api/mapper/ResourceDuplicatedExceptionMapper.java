package malinka.modularapp.jaxrs.api.mapper;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.common.exception.ResourceDuplicatedException;
import malinka.modularapp.common.util.STATUS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This is to handle the resource duplicated exception.
 *
 * @author Malinka Phann
 */
public class ResourceDuplicatedExceptionMapper implements
        ExceptionMapper<ResourceDuplicatedException> {

    /**
     * @param e a thrown exception
     * @return Response a response
     */
    @Override
    public Response toResponse(ResourceDuplicatedException e) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ApiResponse
                        .builder()
                        .status(STATUS.ERROR_DUPLICATED.getValue())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
