package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class FirstBowl extends Running {
    private static final String GUTTER_EXPRESSION = "-";
    private static final int SPARE = 10 ;
    private static final int GUTTER = 0;
    private final Score score;
    private final Pins pins;

    public FirstBowl(int countOfPins) {
        this.score = new Score(countOfPins, 1);
        this.pins = new Pins(countOfPins);
    }

    @Override
    public State bowl(int countOfPins) {
        if(this.score.getScore() + countOfPins == SPARE) {
            return new Spare(this.score.getScore(), countOfPins);
        }
        if(this.score.getScore() == GUTTER && countOfPins == GUTTER) {
            return new Gutter();
        }
        return new Miss(this.score.getScore(), countOfPins);
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public String expression() {
        if(this.score.getScore() == 0) {
            return GUTTER_EXPRESSION;
        }
        return String.valueOf(this.score.getScore());
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
