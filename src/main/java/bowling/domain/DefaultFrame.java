package bowling.domain;

import java.util.List;

public abstract class DefaultFrame implements Frame {

    private static final int FIRST_SCORE = 0;
    private static final int SECOND_SCORE = 1;

    protected final List<Pins> score;

    protected DefaultFrame(List<Pins> score) {
        this.score = score;
    }

    @Override
    public boolean isStrike() {
        return !score.isEmpty() && score.get(FIRST_SCORE).isStrike();
    }

    @Override
    public boolean isSpare() {
        return score.size() > 1 && score.get(FIRST_SCORE).isSpare(score.get(SECOND_SCORE));
    }

}
