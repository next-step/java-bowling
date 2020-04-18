package bowling.domain;

public class Game {
    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public void play(int numberOfPin) {
        player.processPin(numberOfPin);
    }

    public Player getPlay() {
        return this.player;
    }

}
