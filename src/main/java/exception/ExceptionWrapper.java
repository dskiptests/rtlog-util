package exception;

/**
 * Created by david on 2017-03-16.
 */
public class ExceptionWrapper {

    public String exception;
    public String message;
    public StackTraceElement[] stackTrace;
    public String data;

    public ExceptionWrapper(final Exception e, final String data) {
        this.exception = e.getClass().getSimpleName();
        this.message = e.getMessage();
        this.stackTrace = e.getStackTrace();
        this.data = data;
    }

    public ExceptionWrapper(){}
}
