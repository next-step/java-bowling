package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Ordinary extends State {
    private final Pin pins;

    public Ordinary(Pin pins, int leftTry, Scores scores) {
        super(leftTry, scores);
        this.pins = pins;
    }

    @Override
    public State record(Pin pins) {
        if (pins.isAllFell() || this.pins.getFellPins() + pins.getFellPins() > Pin.MAX_FELL_PINS) {
            throw new InvalidRecordInOrdinaryException(pins.getFellPins());
        }
        if (this.pins.isRestFell(pins.getFellPins())) {
            return new Spare(leftTry - 1, scores.add(Score.spare(pins.getFellPins())));
        }
        if (pins.isNoneFell()) {
            return new Gutter(MIN_LEFT_TRY, scores.add(Score.gutter()));
        }
        return new Ordinary(pins, MIN_LEFT_TRY, scores.add(Score.ordinary(pins.getFellPins())));
    }

}
