package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.State;
import bowling.exception.InvalidPitchException;

public class FirstBowl implements Running {

    private static final String GUTTER = "-";

    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State create(Pins firstPins) {
        return new FirstBowl(firstPins);
    }

    @Override
    public State pitch(Pins secondPins) {
        if(firstPins.exceedAllPins(secondPins)) {
            throw new InvalidPitchException(secondPins);
        }

        if (firstPins.isSpare(secondPins)) {
            return Spare.create(firstPins);
        }

        return Miss.of(firstPins, secondPins);
    }

    @Override
    public String getSymbol() {
        return firstPins.isGutter() ? GUTTER : String.valueOf(firstPins);
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        if (beforeScore.finishCalculation()) {
            return beforeScore;
        }

        return beforeScore.addBonusScore(firstPins.count());
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public Score score() {
        return Score.unavailable();
    }

}
