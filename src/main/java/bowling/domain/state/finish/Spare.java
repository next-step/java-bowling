package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.Pins;

public class Spare implements Finish {

    private static final String VERTICAL_BAR = "|";

    private static final String GUTTER = "-";

    private static final String SPARE = "/";

    private final Pins firstPins;

    private Spare(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State create(Pins firstPins) {
        return new Spare(firstPins);
    }

    @Override
    public String getSymbol() {
        return getFirstSymbol() + VERTICAL_BAR + SPARE;
    }

    @Override
    public Score score() {
        return Score.spare();
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        if (beforeScore.finishCalculation()) {
            return beforeScore;
        }

        Score addedScore = beforeScore.addBonusScore(firstPins.count());
        if (addedScore.finishCalculation()) {
            return addedScore;
        }

        Pins secondPins = Pins.spareSecondPins(firstPins);
        return addedScore.addBonusScore(secondPins.count());
    }

    private String getFirstSymbol() {
        return firstPins.isGutter() ? GUTTER : String.valueOf(firstPins);
    }

}
