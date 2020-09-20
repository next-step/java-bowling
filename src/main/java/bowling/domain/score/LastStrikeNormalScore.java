package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public class LastStrikeNormalScore extends AbstractNormalScore {

    public LastStrikeNormalScore(NormalFrame normalFrame) {
        super(normalFrame);
    }

    @Override
    protected int calculateScore() {
        return 0;
    }

    @Override
    public boolean checkValid() {
        return true;
    }

}
