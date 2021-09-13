package bowling.model.player;

public class Player {
    private final PlayerNumber number;
    private final PlayerName name;

    public Player(int number, String name) {
        this.number = new PlayerNumber(number);
        this.name = new PlayerName(name);
    }

    public PlayerNumber number() {
        return number;
    }

    public String name() {
        return name.name();
    }

    public boolean isEqualNumber(PlayerNumber other) {
        return number.equals(other);
    }
}
