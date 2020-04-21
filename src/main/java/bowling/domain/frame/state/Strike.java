package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Strike implements State {

    private static final String CANT_THROW_BALL = "더이상 투구할 수 없습니다";

    private final Pins pins;

    public Strike() {
        this.pins = Pins.from();
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getCurrentScore() {
        return new Score(pins.getDownPins(), 2);
    }

    @Override
    public Score getCalculateScore(Score before) {
        return before.addScore(new Score(Pin.MAX_PIN));
    }

    @Override
    public Pins getPins() {
        return pins;
    }
}
