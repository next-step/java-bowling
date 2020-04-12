package bowling.domain;

public class Game {
    private Player player;
    private Frame frame;

    public Game(Player player) {
        this.player = player;
        this.frame = new Frame();
    }

    public int currentFrame() {
        return player.currentFrame();
    }

    public void play(int numberOfPin) {
        if (playFinal(numberOfPin)) {
            return;
        }
        playNormal(numberOfPin);
    }

    private boolean playFinal(int numberOfPin) {
        if (player.isEndNormalFrame() && !player.isEndFinalFrame()) {
            frame.addFinalFrame(numberOfPin);
            FinalFrame finalFrame = new FinalFrame(frame);
            player.addFrame(finalFrame);
            return true;
        }
        return false;
    }

    private void playNormal(int numberOfPin) {
        frame.addNormalFrame(numberOfPin);
        if (!player.isEndNormalFrame() && frame.isNextFrame()) {
            NormalFrame normalFrame = new NormalFrame(frame);
            player.addFrame(normalFrame);
            frame = new Frame();
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player +
                ", frame=" + frame +
                '}';
    }
}
