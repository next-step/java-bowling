package bowling.domain;

public class Player {

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        validateName(name);
        return new Player(name);
    }

    private static void validateName(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("이름을 입력해 주세요");
        }
    }

    public String getName() {
        return name;
    }
}
