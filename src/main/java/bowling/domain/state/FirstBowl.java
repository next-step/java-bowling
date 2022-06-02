package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class FirstBowl extends Running {
    private static final String GUTTER_EXPRESSION = "-";
    private final Pins pins;

    public FirstBowl(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int countOfPins) {
        Pins secondPins = new Pins(countOfPins);
        if(pins.isSpare(secondPins)) {
            return new Spare();
        }
        return new Miss(pins, secondPins);
    }

    @Override
    public String expression() {
        if(pins.isGutter()) {
            return GUTTER_EXPRESSION;
        }
        return pins.expression();
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        throw new BowlingGameException("FirstBowl 상태에서는 점수를 계산할 수 없습니다.");
    }
}
