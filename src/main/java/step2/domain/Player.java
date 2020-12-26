package step2.domain;

public class Player {

    private static final int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(name);
    }

    private void validateName(String name) {
        if(name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 3글자여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
