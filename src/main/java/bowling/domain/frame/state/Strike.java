package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.exception.BowlingException;

public class Strike implements State {

    private static final String CANT_THROW_BALL = "더이상 투구할 수 없습니다";

    private final Pins firstPins = new Pins(0);

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
