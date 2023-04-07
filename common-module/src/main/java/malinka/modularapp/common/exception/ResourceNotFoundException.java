package malinka.modularapp.common.exception;

/**
 * When something is not found.
 *
 * @author Malinka Phann
 */
public class ResourceNotFoundException extends GeneralException {

    public ResourceNotFoundException(Throwable t) {
        super(t);
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
