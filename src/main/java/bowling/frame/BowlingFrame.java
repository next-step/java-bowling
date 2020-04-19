package bowling.frame;

import bowling.FrameScore;
import bowling.Score;
import bowling.framestate.State;

public interface BowlingFrame {

    static BowlingFrame createFirstFrame() {
        return newInstance(1);
    }

    static BowlingFrame newInstance(final int frameNumber) {
        if (frameNumber == 10) {
            return LastBowlingFrame.newInstance();
        }
        return CommonBowlingFrame.newInstance();
    }

    void bowl(int countOfPin);

    Score getFrameScore();

    Score getTotalScore(Score beforeScore);

    Score addingUpScore(FrameScore beforeScore);

    boolean isOver();

    BowlingFrame addNextFrame(int frameNumber);

    boolean canCalculateScore();

    boolean canCalculateScore(FrameScore frameScore);

    State getState();
}
