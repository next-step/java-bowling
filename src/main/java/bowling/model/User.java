package bowling.model;

import static bowling.model.BowlingValidator.isBlank;

public class User {
    private final String name;

    public String getName() {
        return name;
    }

    public User(String name) {
        isBlank(name);
        isValid(name);
        this.name = name.trim();
    }

    private void isValid(String name) {
        if (!name.trim().matches("[A-Za-z]{3}")) {
            throw new IllegalArgumentException("영문으로 3자 입력해주세요.");
        }
    }
}
