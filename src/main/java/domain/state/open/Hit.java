package domain.state.open;

import domain.Pins;
import domain.Score;
import domain.state.State;
import domain.state.closed.Miss;
import domain.state.closed.Spare;

import static domain.state.closed.Miss.GUTTER_SYMBOL;

public class Hit extends Open {

    private Pins firstFallenPins;

    Hit(Pins fallenPins) {
        this.firstFallenPins = fallenPins;
    }

    @Override
    public State update(Pins secondFallenPins) {
        if (firstFallenPins.isSpare(secondFallenPins)) {
            return new Spare(firstFallenPins, secondFallenPins);
        }
        return new Miss(firstFallenPins, secondFallenPins);
    }

    @Override
    public String printState() {
        if (firstFallenPins.isMatch(Pins.from(0))) {
            return GUTTER_SYMBOL;
        }
        return firstFallenPins.toString();
    }

    @Override
    public Score updateScore(Score score) {
        return score.update(score.getScore());
    }

    @Override
    public Score getScore() {
        return Score.of(firstFallenPins.getFallenPins(), 1);
    }
}
