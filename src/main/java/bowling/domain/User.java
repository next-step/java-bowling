package bowling.domain;

public class User {
    private static final String MAX_LENGTH_OVER = "이름은 최대 3글자입니다.";
    private static final int MAX_LENGTH = 3;
    private final String name;

    public User(String name) {
        validMaxLength(name);
        this.name = name;
    }

    private void validMaxLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH_OVER);
        }
    }

    public String name() {
        return name;
    }
}
