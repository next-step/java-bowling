package bowling.domain;

public class Player {
    private final String name;

    private Player(String name) {
        valid(name);
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    private void valid(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("이름은 3글자까지 허용합니다.");
        }
    }
}
