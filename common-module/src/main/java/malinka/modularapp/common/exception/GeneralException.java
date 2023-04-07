package malinka.modularapp.common.exception;

/**
 * This is the parent exception to all custom exceptions.
 *
 * @author Malinka Phann
 */
public class GeneralException extends RuntimeException {

    public GeneralException(Throwable t) {
        super(t);
    }

    public GeneralException(String msg) {
        super(msg);
    }

    public GeneralException(String msg, Throwable t) {
        super(msg, t);
    }
}
