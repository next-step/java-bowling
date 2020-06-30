package bowling.domain.frame;

import bowling.domain.Score;

public class FinalFrame extends Frame implements FrameFactory {

    private static final int LIMIT_COUNT = 3;
    private static final int FINAL_FRAME_MINIMUN_COUNT = 1;

    @Override
    public int moveNextFrame() {
        if (scores.size() == LIMIT_COUNT || validateFinalFrame()) {
            return 1;
        }
        return 0;
    }

    private boolean validateFinalFrame() {
        if (isStrike()) {
            return false;
        }
        if (isSpare()) {
            return false;
        }
        return !(scores.size() == FINAL_FRAME_MINIMUN_COUNT);
    }

    public boolean isFirst() {
        return scores.size() == 0;
    }

    public boolean isStrike() {
        return scores.get(scores.size() - 1).validateMaxScore();
    }

    public boolean isMiss() {
        return scores.get((scores.size() - 1)).validateMinScore();
    }

    public boolean isSpare() {
        if(scores.size() < Frame.LIMIT_COUNT) {
            return false;
        }

        if(super.isSpare() && scores.size() == LIMIT_COUNT) {
            return false;
        }

        return scores.get(scores.size() - 2).getScore()
                + scores.get(scores.size() - 1).getScore()
                == Score.MAX_SCORE;
    }

    public int getFrameLastScore() {
        return scores.get((scores.size() - 1)).getScore();
    }
}
