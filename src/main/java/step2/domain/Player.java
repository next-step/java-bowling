package step2.domain;

public class Player {

    private static final int LENGTH_CONDITION = 3;

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() != LENGTH_CONDITION) {
            throw new IllegalArgumentException("이름은 3글자로 입력해주세요.");
        }
    }

    public static Player from(String name) {
        return new Player(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
