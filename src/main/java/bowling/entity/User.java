package bowling.entity;

public class User {

    private static final int LENGTH_LIMIT = 3;

    private final String name;

    public User(String name) {
        this.name = name;
    }

    private void throwIfLengthLimitOver(String name) {
        if (name.length() != LENGTH_LIMIT) {
            throw new IllegalArgumentException("이름은 영어 3글자만 입력 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
