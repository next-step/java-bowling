package bowling.frame;

import bowling.FrameScore;
import bowling.Pin;
import bowling.Score;
import bowling.dto.FrameState;
import bowling.framestate.State;

import static bowling.frame.BowlingFrames.MAX_BOWLING_FRAME_SIZE;

public interface BowlingFrame {

    static BowlingFrame createFirstFrame() {
        return newInstance(1);
    }

    static BowlingFrame newInstance(final int frameNumber) {
        if (frameNumber == MAX_BOWLING_FRAME_SIZE) {
            return LastBowlingFrame.newInstance();
        }
        return CommonBowlingFrame.newInstance();
    }

    void bowl(Pin pinCount);

    Score getTotalScore(Score beforeScore);

    Score getFrameScore();

    Score sumScore(FrameScore beforeScore);

    BowlingFrame appendNextFrame(int frameNumber);

    boolean isOver();

    boolean canCalculateScore();

    boolean canCalculateScore(FrameScore frameScore);

    State getState();

    FrameState makeFrameState();
}
