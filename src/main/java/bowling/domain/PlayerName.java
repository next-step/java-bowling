package bowling.domain;

public class PlayerName {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 3글자로 지정해야 합니다.";
    private final String name;


    public PlayerName(String name) {
        validNameCheck(name);
        this.name = name;
    }

    private void validNameCheck(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
