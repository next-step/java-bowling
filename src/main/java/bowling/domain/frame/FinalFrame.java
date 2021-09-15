package bowling.domain.frame;

import bowling.domain.score.Score;

public class FinalFrame extends Frame {

    FinalFrame(int round, Score score, Frame nextFrame) {
        super(round, score, nextFrame);
    }

    @Override
    public Frame createNextFrame() {
        return null;
    }

    @Override
    public void saveScore(Score score) {

    }
}
