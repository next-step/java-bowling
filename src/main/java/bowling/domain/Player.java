package bowling.domain;

public class Player {
    private static final int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 null 일 수 없습니다.");
        }
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름 길이는 3자여야 합니다. 전달 받은 이름 길이 : %d", name.length()));
        }
    }

    public String name() {
        return this.name;
    }
}