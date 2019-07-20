package domain.state;

import domain.Pins;
import domain.score.BonusType;
import domain.score.Score;

public class Strike implements State {

    private final static String STRIKE = " X ";
    private final Pins first;
    private final Pins second;

    public Strike(Pins downPins) {
        verify(downPins);
        this.first = downPins;
        this.second = Pins.EMPTY;
    }

    private void verify(Pins downPins) {
        if (isNotStrike(downPins)) {
            throw new IllegalArgumentException("스트라이크 아님");
        }
    }

    private boolean isNotStrike(Pins downPins) {
        return !Pins.ALL.equals(downPins);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore.calculate(getScore());
    }

    @Override
    public Score getScore() {
        return Score.of(first, second, BonusType.strike());
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public String toSymbol() {
        return STRIKE;
    }
}
