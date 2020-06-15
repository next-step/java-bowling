package bowling.step3.domain.frame;

import bowling.step3.domain.scores.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores, Frame prevFrame) {
        super(frame, scores, prevFrame);
    }

    public static Frame of(int frame, Scores scores, Frame prevFrame) {
        return new FinalFrame(frame, scores, prevFrame);
    }

    @Override
    public Frame createNextFrame(Scores scores) {
        return of(frame, scores, prevFrame);
    }
}