package domain.state;

import domain.Pins;
import domain.score.BonusType;
import domain.score.Score;

import static io.OutputResult.EMPTY_SPACE;

public class Waiting implements State {

    private final Pins first;
    private final Pins second;

    public Waiting(Pins downPins) {
        this.first = downPins;
        this.second = Pins.ZERO;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.of(first, second, BonusType.normal());
//        return Score.EMPTY;
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score calculate = beforeScore.calculate(getScore());
        if(calculate.hasBonus()) {
            return Score.EMPTY;
        }
        Score waiting = Score.of(first, second, BonusType.normal());
        return waiting.calculate(beforeScore);
    }

    @Override
    public String toSymbol() {
        return first + "|";
    }
}
