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
        if (player.isEndNormalFrame() && !player.isEndFinalFrame()) {
            frame.addFinalFrame(numberOfPin);
            FinalFrame finalFrame = new FinalFrame(frame);
            player.addFrame(finalFrame);
            return;
        }
        playNormal(numberOfPin);
    }

    private void playNormal(int numberOfPin) {
        frame.addNormalFrame(numberOfPin);
        if (!player.isEndNormalFrame() && frame.isNextFrame()) {
            NormalFrame normalFrame = new NormalFrame(frame);
            player.addFrame(normalFrame);
            frame = new Frame();
        }
    }

    public Player getPlay() {
        return this.player;
    }

    public Frame getFrame() {
        return frame;
    }
}
