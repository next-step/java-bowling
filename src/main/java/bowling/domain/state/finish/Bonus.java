package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.state.BonusStateCreateScoreException;

public class Bonus extends Finish {

    private final Pin bonusPin;

    public Bonus(Pin bonusPin) {
        this.bonusPin = bonusPin;
    }

    @Override
    public Score createScore() {
        throw new BonusStateCreateScoreException();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.addPin(bonusPin);
    }

}
