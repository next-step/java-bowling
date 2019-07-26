package domain.state.open;

import domain.Pins;
import domain.Score;
import domain.state.State;
import domain.state.closed.Miss;
import domain.state.closed.Spare;

import static domain.state.closed.Miss.GUTTER_SYMBOL;

public class Hit extends Open {

    private Pins firstFallenPins;

    public Hit(Pins fallenPins) {
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
        if (firstFallenPins.isMatch(Pins.GUTTER)) {
            return GUTTER_SYMBOL;
        }
        return firstFallenPins.toString();
    }

    @Override
    public Score updateScore(Score score) {
        return score.update(firstFallenPins.getFallenPins());
    }

    @Override
    public Score getScore() {
        return Score.of(firstFallenPins.getFallenPins(), 0);
    }
}
