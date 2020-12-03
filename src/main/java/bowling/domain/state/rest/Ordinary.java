package bowling.domain.state.rest;

import bowling.domain.pin.Pin;
import bowling.domain.score.Scores;

public class Ordinary extends HasRestPins {
    public Ordinary(Pin pins, int leftTry, Scores scores) {
        super(pins, leftTry, scores);
    }
}
