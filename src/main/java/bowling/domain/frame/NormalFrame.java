package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

public class NormalFrame extends AbstractFrame {

    private Frame nextFrame;

    public NormalFrame(int round, Score score, Frame nextFrame) {
        super(round, score);
        this.nextFrame = nextFrame;
    }

    static Frame of(int round, Score score, Frame nextFrame) {
        return new NormalFrame(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_ROUND, NormalScore.emptyScore(), null);
    }

    @Override
    public Frame createNextFrame() {
        if (round() + 1 == FinalFrame.FINAL_ROUND) {
            return this.nextFrame = FinalFrame.of(FinalScore.emptyScore());
        }
        return this.nextFrame = NormalFrame.of(round() + 1, NormalScore.emptyScore(), null);
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

}
