package bowling.domain;

public class PlayerName {
    private final String name;

    public PlayerName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (name.trim().length() > 3) {
            throw new IllegalArgumentException("이름은 세글자까지만 입력할 수 있습니다.");
        }
        if (isNotAlphabet(name)) {
            throw new IllegalArgumentException("이름은 알파벳만 입력할 수 았습니다.");
        }
        this.name = name;
    }

    private boolean isNotAlphabet(String name) {
        return !name.trim().matches("[a-zA-Z]+");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
