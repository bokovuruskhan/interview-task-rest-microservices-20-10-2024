package bokovuruskhan.roles_rest.util;

public class RestUtils {
    public static ErrorDescription prepareErrorBody(Exception ex) {
        return ErrorDescription.builder()
                .message(ex.getMessage())
                .build();
    }
}
