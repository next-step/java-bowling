package bowling.domain.player;

public class Player {
    private static final int NAME_LENGTH = 3;
    private final String name;

    public Player(final String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3자리 영문이여야 합니다.");
        }
        this.name = name;
    }

    public String print() {
        return name;
    }
}
