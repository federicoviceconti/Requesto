package exception;

/**
 *
 */
public class MalformedBuilderParameterException extends RuntimeException {
    public static final String NOT_FORMED_PARAM = "Error when build request with: ";

    public MalformedBuilderParameterException(String message) {
        super(NOT_FORMED_PARAM + message);
    }
}
