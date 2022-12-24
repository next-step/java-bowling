package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public class Strike extends Finished {

    @Override
    public Score getScore() {
        return new Score(Pin.MAX_PIN, 2);
    }

    @Override
    public Score addBonusScore(Score beforeScore) {
        return beforeScore.bowl(Pin.MAX_PIN);
    }

    @Override
    public String toString() {
        return "X";
    }
}
