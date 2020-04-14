package bowling.domain;

public class Game {
    private Player player;

    public Game(Player player) {
        this.player = player;
    }

    public int currentFrame() {
        return player.currentFrame();
    }

    public void play(int numberOfPin) {
        if (player.isEndNormalFrame() && !player.isEndFinalFrame()){
            player.addFinalFrame(numberOfPin);
            return;
        }
        playNormal(numberOfPin);
    }

    private void playNormal(int numberOfPin) {
        player.addNormalFrame(numberOfPin);
    }

    public Player getPlay() {
        return this.player;
    }
}
