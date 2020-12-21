package bowling.model.state.finished;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.State;

public class Spare extends FinishedState {
    private static final String SPARE_TOTAL_SCORE_ERROR = "Spare의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "/";

    private final Pins firstFallenPins;

    private Spare(Pins firstFallenPins, Pins secondPins) {
        super(secondPins);
        this.expression = EXPRESSION;
        this.firstFallenPins = firstFallenPins;
    }

    public static State of(Pins firstFallenPins, Pins secondFallenPins) {
        Pins totalScore = firstFallenPins.add(secondFallenPins);

        if (!totalScore.isMaxScore() || firstFallenPins.isMaxScore()) {
            throw new IllegalArgumentException(SPARE_TOTAL_SCORE_ERROR);
        }
        return new Spare(firstFallenPins, secondFallenPins);
    }

    @Override
    public Score score() {
        return Score.of(10, 1);
    }

    @Override
    public Score calculateScore(Score score) {
        Score firstAddition = score.add(firstFallenPins.getScore());
        return firstAddition.canCalculate() ? firstAddition : firstAddition.add(pins.getScore());
    }
}
