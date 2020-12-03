package bowling.domain.state.rest;

import bowling.domain.pin.Pin;
import bowling.domain.score.Scores;

public class Gutter extends HasRestPins {
    public Gutter(int leftTry, Scores scores) {
        super(Pin.of(0), leftTry, scores);
    }
}
