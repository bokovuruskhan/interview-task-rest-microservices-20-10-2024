package bokovuruskhan.roles_rest.controller.ex;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
