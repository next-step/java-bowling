package bowling.model.state;

import bowling.model.Score;

public class Strike extends Finished {
    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(Pin.MAX_PINS);
    }

    public Score getScore() {
        return new Score(Pin.MAX_PINS, 2);
    }

    @Override
    public String getDesc() {
        return "X";
    }
}
