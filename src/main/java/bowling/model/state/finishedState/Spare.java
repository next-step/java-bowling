package bowling.model.state.finishedState;

import bowling.model.Pins;
import bowling.model.state.State;

public class Spare extends FinishedState {
    private static final String SPARE_TOTAL_SCORE_ERROR = "Spare의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "/";

    private final Pins totalScore;

    private Spare(Pins pins, Pins totalScore) {
        super(pins);
        this.expression = EXPRESSION;
        this.totalScore = totalScore;
    }

    public static State of(Pins pins, Pins totalScore) {
        if (!totalScore.isMaxScore() || pins.isMaxScore()) {
            throw new IllegalArgumentException(SPARE_TOTAL_SCORE_ERROR);
        }
        return new Spare(pins, totalScore);
    }

    @Override
    public boolean isMaxScore() {
        return totalScore.isMaxScore();
    }
}
