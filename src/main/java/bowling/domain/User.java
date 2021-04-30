package bowling.domain;

public class User {
    private final String user;

    public User(String user) {
        if (user.length() > 3) {
            throw new IllegalArgumentException("최대 3자의 영어글자만 가능합니다.");
        }
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

}
