package bokovuruskhan.common.util;

public class RestUtils {

    public final static String API_PREFIX = "/api";

    public static ErrorDescription prepareErrorBody(Exception ex) {
        return ErrorDescription.builder()
                .message(ex.getMessage())
                .build();
    }
}
