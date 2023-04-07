package malinka.modularapp.common.util;

import malinka.modularapp.common.exception.ValidationException;
import java.util.Objects;

/**
 * This is the validation util.
 * The validation might be better to be replaced by the javax.validation.
 *
 * @author Malinka Phann
 */
public class ValidationUtil {

    /**
     * Validate against null.
     *
     * @param key a label
     * @param value a validated value
     * @throws NullPointerException when the input param is null
     * @throws ValidationException when fail
     */
    public static void checkNull(String key, Object value) {
        Objects.requireNonNull(key, "the input key must not be null");
        if (Objects.isNull(value)) {
            throw new ValidationException(String.format(
                    "%s must not be null", key));
        }
    }

    /**
     * Validate against empty.
     *
     * @param key a label
     * @param value a validated value
     * @throws NullPointerException when the input param is null
     * @throws ValidationException when fail
     */
    public static void checkEmpty(String key, String value) {
        Objects.requireNonNull(key, "the input key must not be null");
        if (value.isEmpty()) {
            throw new ValidationException(String.format(
                    "%s must not be empty", key));
        }
    }

    /**
     * Validate against min value.
     *
     * @param key a label
     * @param value a validated value
     * @param min a minimum value of allowance
     * @throws NullPointerException when the input param is null
     * @throws ValidationException when fail
     */
    public static void checkMinLen(String key, int value, int min) {
        Objects.requireNonNull(key, "the input key must not be null");
        if (value < min) {
            throw new ValidationException(String.format(
                    "%s is invalid, its length must be greater than %d characters long",
                    key, min));
        }
    }

    /**
     * Validate against max value.
     *
     * @param key a label
     * @param value a validated value
     * @param max a maximum value of allowance
     * @throws NullPointerException when the input param is null
     * @throws ValidationException when fail
     */
    public static void checkMaxLen(String key, String value, int max) {
        Objects.requireNonNull(key, "the input key must not be null");
        if (value.length() > max) {
            throw new ValidationException(String.format(
                    "%s is invalid, its length must be lower than %d characters long",
                    key, max));
        }
    }
}
