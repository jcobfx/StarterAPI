package pl.com.foks.starterapi.users.domain;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String email) {
        super("User with email: " + email + " already exists");
    }
}
