package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class FirstBowl extends State {

    private final Pin pin;

    public FirstBowl(Pin pin) {
        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {
        if (this.pin.add(pin).isKnockDown()) {
            return new Spare(this.pin, pin);
        }

        return new Miss(this.pin, pin);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        return new Score(pin.getCount(), 1);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.add(pin.getCount());
    }

    @Override
    public List<Pin> pins() {
        return List.of(pin);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFirstBowl() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
