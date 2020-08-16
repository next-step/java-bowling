package bowling.domian.state.finished;

import bowling.domian.frame.Score;

public class Strike extends Finished {
    @Override
    public Score getScore() {
        return Score.strike();
    }

    @Override
    public Score calculateAdditional(Score score) {
        score = score.additionalBowl(10);

        return score;
    }

    public String getDesc() {
        return null;
    }
}
