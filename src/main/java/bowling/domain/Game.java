package bowling.domain;

public class Game {
    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public void play(int numberOfPin) {
        if (player.isFinal()){
            player.addFinalFrame(numberOfPin);
            return;
        }
        player.addNormalFrame(numberOfPin);
    }

    public Player getPlay() {
        return this.player;
    }
}
