package bowling.domain.frame;

import static bowling.domain.frame.Frames.FINAL_FRAME_ROUND;
import static bowling.domain.frame.Frames.FIRST_FRAME_ROUND;

import bowling.domain.score.FinalScore;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

public class NormalFrame extends Frame {

    private Frame nextFrame;

    private NormalFrame(int round, Score score, Frame nextFrame) {
        super(round, score);
        this.nextFrame = nextFrame;
    }

    static Frame of(int round, Score score, Frame nextFrame) {
        return new NormalFrame(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_FRAME_ROUND, NormalScore.empty(), null);
    }

    @Override
    public Frame createNextFrame() {
        int nextRound = round + 1;
        if (nextRound < FINAL_FRAME_ROUND) {
            return this.nextFrame = new NormalFrame(round + 1, NormalScore.empty(), null);
        }
        return this.nextFrame = FinalFrame.of(FINAL_FRAME_ROUND, FinalScore.empty());
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

}
