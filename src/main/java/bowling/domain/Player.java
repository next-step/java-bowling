package bowling.domain;

public class Player {

    public static final int NAME_LENGTH_CONDITION = 3;
    public static final String LENGTH_LIMIT = "이름은 3글자여야 합니다.";
    public static final String NAME_EMPTY = "이름을 입력해주세요.";

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(name);
    }

    private void validateName(String name) {
        validateLength(name);
        validateEmpty(name);
    }

    private void validateLength(String name) {
        if(name.length() != NAME_LENGTH_CONDITION) {
            throw new IllegalArgumentException(LENGTH_LIMIT);
        }
    }

    private void validateEmpty(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(NAME_EMPTY);
        }
    }

    public String getName() {
        return name;
    }
}
