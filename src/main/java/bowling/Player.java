package bowling;

public class Player {
    public static final int NAME_LENGTH = 3;
    public static final String ONLY_ENG_ALPHABETS = "^[a-zA-Z]*$";

    private final String name;

    public Player(String name) {
        validate(name);

        this.name = name;
    }

    private void validate(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Player name null exception.");
        }

        if (!isLengthThree(name)) {
            throw new IllegalArgumentException("플레이어의 이름은 3자리 입니다.");
        }

        if (!isOnlyAlphabetics(name)) {
            throw new IllegalArgumentException("플레이어의 영문자 입니다.");
        }
    }

    private boolean isOnlyAlphabetics(String nonNullName) {
        return nonNullName.matches(ONLY_ENG_ALPHABETS);
    }

    private boolean isLengthThree(String nonNullName) {
        return nonNullName.length() == NAME_LENGTH;
    }

    public String getNameString() {
        return name;
    }
}
