package bowling.model;

public class User {
    private final String name;

    public User(String name) {
        isValid(name);
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    private void isValid(String name) {
        if (!name.trim().matches("[A-Za-z]{3}")) {
            throw new IllegalArgumentException("영문으로 3자 입력해주세요.");
        }
    }
}
