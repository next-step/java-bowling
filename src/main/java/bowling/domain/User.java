package bowling.domain;

public class User {
    private static final String NAME_FORMAT = "|  %s |";
    private static final int NAME_LENGTH = 3;

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 사용자의 이름은 " + NAME_LENGTH + "글자여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.format(NAME_FORMAT, name);
    }
}
