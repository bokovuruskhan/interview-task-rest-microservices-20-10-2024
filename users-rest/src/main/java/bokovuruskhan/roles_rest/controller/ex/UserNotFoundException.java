package bokovuruskhan.roles_rest.controller.ex;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
