package bowling.domain;

public class User {
    private final String user;

    public User(String user) {
        if (user.length() > 3) {
            throw new IllegalArgumentException("Max 3 english letters Only");
        }
        this.user = user;
    }

}
