package bokovuruskhan.users_roles_rest.util;

import bokovuruskhan.common.util.RestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;


public class RequestBaseLogic {
    static public ResponseEntity<?> handleRequest(Callable<ResponseEntity> logic) {
        try {
            return logic.call();
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestUtils.prepareErrorBody(ex));
        }

    }
}
