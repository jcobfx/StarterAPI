package pl.com.foks.starterapi.users.domain;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with email: " + email + " not found");
    }
}
