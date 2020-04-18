package bowling;

public interface BowlingFrame {

    static BowlingFrame newInstance(final int frameNumber) {
        if (frameNumber == 10) {
            return LastBowlingFrame.newInstance();
        }
        return CommonBowlingFrame.newInstance();
    }

    void bowl(int countOfPin);

    Score getScore();

    Score addingUpScore(FrameScore beforeScore);

    boolean isOver();

    BowlingFrame addNextFrame(int frameNumber);
}
