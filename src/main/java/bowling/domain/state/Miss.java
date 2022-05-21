package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;

public class Miss implements State {

    private static final String GUTTER = "-";
    private static final String VERTICAL_BAR = "|";

    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(Miss.class.getName());
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String getSymbol() {
        return convertIfGutter(firstPins) + VERTICAL_BAR + convertIfGutter(secondPins);
    }

    @Override
    public Score score() {
        int sum = firstPins.add(secondPins);
        return Score.miss(Pins.create(sum));
    }

    @Override
    public final Score calculateScore(Score beforeScore) {
        if (beforeScore.finishCalculation()) {
            return beforeScore;
        }

        Score addedScore = beforeScore.addBonusScore(firstPins.count());
        if (addedScore.finishCalculation()) {
            return addedScore;
        }

        return addedScore.addBonusScore(secondPins.count());
    }
    private String convertIfGutter(Pins pins) {
        return pins.isGutter() ? GUTTER : pins.toString();
    }

}
