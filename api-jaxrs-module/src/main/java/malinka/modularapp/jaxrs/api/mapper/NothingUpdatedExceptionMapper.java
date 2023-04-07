package malinka.modularapp.jaxrs.api.mapper;

import malinka.modularapp.api.respone.ApiResponse;
import malinka.modularapp.common.exception.NothingUpdatedException;
import malinka.modularapp.common.util.STATUS;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This is to handle the nothing updated exception.
 *
 * @author Malinka Phann
 */
public class NothingUpdatedExceptionMapper implements
        ExceptionMapper<NothingUpdatedException> {

    /**
     * @param e a thrown exception
     * @return Response a response
     */
    @Override
    public Response toResponse(NothingUpdatedException e) {
        return Response
                .status(Response.Status.OK)
                .entity(ApiResponse
                        .builder()
                        .status(STATUS.ERROR_NOTHING_UPDATED.getValue())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
