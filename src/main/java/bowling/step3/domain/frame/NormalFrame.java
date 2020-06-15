package bowling.step3.domain.frame;

import bowling.step3.domain.ScoreType;
import bowling.step3.domain.scores.NormalScores;
import bowling.step3.domain.Frames;
import bowling.step3.domain.scores.FinalScores;
import bowling.step3.domain.scores.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frame, Scores scores, Frame prevFrame) {
        super(frame, scores, prevFrame);
    }

    public static NormalFrame of(int frame, Scores scores, Frame prevFrame) {
        return new NormalFrame(frame, scores, prevFrame);
    }

    @Override
    public Frame createNextFrame(Scores scores) {
        Frame now = of(frame, scores, prevFrame);
        if (!scores.isType(ScoreType.STRIKE) && !scores.isFullOf()) {
            return now;
        }
        int nextFrameValue = frame + 1;
        return nextFrameValue < Frames.LAST_FRAME
                ? of(nextFrameValue, NormalScores.init(), now)
                : FinalFrame.of(nextFrameValue, FinalScores.init(), now);
    }
}