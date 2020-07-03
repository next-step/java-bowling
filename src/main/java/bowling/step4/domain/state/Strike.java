package bowling.step4.domain.state;

import bowling.step4.domain.score.Score;

public class Strike extends Finished {

    private static final String STRIKE_DISPLAY = "X";

    @Override
    public String display() {
        return STRIKE_DISPLAY;
    }

    @Override
    public Score getScore() {
        return Score.strike();
    }

    @Override
    public Score addAdditionalScore(Score prevScore) {
        return prevScore.addScore(getScore());
    }
}
