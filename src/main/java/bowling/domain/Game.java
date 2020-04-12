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
        if (player.isEndNormalFrame()){
            if (!player.isEndFinalFrame()) {
                frame.addFinalFrame(numberOfPin);
                FinalFrame finalFrame = new FinalFrame();
                finalFrame.add(frame.getScores());
                player.addFrame(finalFrame);
            }
            return;
        }
        frame.addNormalFrame(numberOfPin);
        if(!player.isEndNormalFrame() && frame.isNextFrame()){
            NormalFrame normalFrame = new NormalFrame();
            normalFrame.add(frame.getScores());
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
