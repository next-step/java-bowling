package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

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

    public String describe() {
        return "" + pin.getCount();
    }
}
