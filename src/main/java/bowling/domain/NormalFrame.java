package bowling.domain;

import static bowling.BowlingApp.getPinCalculateStrategy;
import static bowling.domain.Frames.LAST_FRAME_ORDER;

public class NormalFrame extends DefaultFrame {

    public static final int MAX_SCORE_SIZE = 2;

    public NormalFrame(Scores scores) {
        super(scores);
        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException("score size cannot be bigger than 2");
        }
    }

    public NormalFrame(Scores scores, int order) {
        super(scores, order);
    }

    @Override
    public Frame nextRound() {
        int nextOrder = order + 1;
        if (nextOrder == LAST_FRAME_ORDER) {
            return new FinalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
        }
        return new NormalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
    }

}
