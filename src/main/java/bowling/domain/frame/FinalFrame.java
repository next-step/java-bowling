package bowling.domain.frame;

import bowling.domain.Score;

public class FinalFrame extends Frame implements FrameInterface {

    private static final int LIMIT_COUNT = 3;
    private static final int FIRST_COUNT = 0;
    private static final int SECOND_COUNT = 1;

    @Override
    public boolean validateLimitScore() {
        return scores.size() == LIMIT_COUNT || hasSpare() ;
    }

    private boolean hasSpare() {
        if (hasStrike()) {
            return false;
        }
        if (scores.get(FIRST_COUNT).getScore()
                + scores.get(SECOND_COUNT).getScore()
                == Score.MAX_SCORE) {
            return false;
        }

        return true;
    }
}
