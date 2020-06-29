package bowling.model;

public class Player {

    private static final int MAX_SIZE = 3;

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(final String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(String name) {
        if (name == null || name.length() != MAX_SIZE) {
            throw new IllegalArgumentException("사용자 이름은 3글자로 지정해야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
