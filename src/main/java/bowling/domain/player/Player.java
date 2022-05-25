package bowling.domain.player;

public class Player {
    private final PlayerName name;

    public Player(PlayerName name) {
        validate(name);
        this.name = name;
    }

    private void validate(PlayerName name) {
        if (name == null) {
            throw new IllegalArgumentException("PlayerName은 null 일 수 없습니다.");
        }
    }
}
