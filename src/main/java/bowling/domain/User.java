package bowling.domain;

public class User {
    private static final String MAX_LENGHT_OVER = "이름은 최대 3글자입니다.";
    private static final int MAX_LENGHT = 3;
    private final String name;

    public User(String name) {
        validMaxLength(name);
        this.name = name;
    }

    private void validMaxLength(String name) {
        if (name.length() > MAX_LENGHT) {
            throw new IllegalArgumentException(MAX_LENGHT_OVER);
        }
    }

    public String name() {
        return name;
    }
}
