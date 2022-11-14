package bowling.domain;

import static bowling.BowlingApp.getPinCalculateStrategy;

public class NormalFrame extends DefaultFrame {

    public NormalFrame(Scores scores) {
        super(scores);
        if (scores.size() > 2) {
            throw new IllegalArgumentException("score size cannot be bigger than 2");
        }
    }

    public NormalFrame(Scores scores, int order) {
        super(scores, order);
    }

    @Override
    public Frame nextRound() {
        int nextOrder = order + 1;
        if (nextOrder == 10) {
            return new FinalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
        }
        return new NormalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
    }

}
