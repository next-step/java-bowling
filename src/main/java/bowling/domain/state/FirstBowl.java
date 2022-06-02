package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class FirstBowl extends Running {
    private static final String GUTTER_EXPRESSION = "-";
    private static final int SPARE = 10 ;
    private static final int GUTTER = 0;
    private final Pins pins;

    public FirstBowl(int countOfPins) {
        this.pins = new Pins(countOfPins);
    }

    @Override
    public State bowl(int countOfPins) {
        if(this.pins.getPins() + countOfPins == SPARE) {
            return new Spare(this.pins.getPins(), countOfPins);
        }
        if(this.pins.getPins() == GUTTER && countOfPins == GUTTER) {
            return new Gutter();
        }
        return new Miss(this.pins.getPins(), countOfPins);
    }

    @Override
    public String expression() {
        if(this.pins.getPins() == 0) {
            return GUTTER_EXPRESSION;
        }
        return String.valueOf(this.pins.getPins());
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        beforeScore = beforeScore.bowl(this.pins.getPins());
        if(beforeScore.isCalculateScore()) {
            return beforeScore;
        }
        throw new BowlingGameException("스코어에 점수추가가 불가능 합니다.");
    }
}
