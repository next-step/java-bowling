package bowling.domain;

import static bowling.BowlingApp.getPinCalculateStrategy;

public class NormalFrame extends DefaultFrame {

    public NormalFrame(Score score) {
        super(score);
    }

    public NormalFrame(Score score, int order) {
        super(score, order);
    }

    @Override
    public NormalFrame nextRound(){
        int nextOrder = order + 1;
        return new NormalFrame(new Score(getPinCalculateStrategy()), nextOrder);
    }

}
