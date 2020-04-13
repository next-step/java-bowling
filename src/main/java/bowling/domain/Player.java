package bowling.domain;

public class Player {
    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("플레이어 이름은 3글자 입니다.");
        }
    }

    public String getName() {
        return name;
    }
}
