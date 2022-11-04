package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.ScoreV2;

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
    public ScoreV2 getScore() {
        return new ScoreV2(pin.getCount(), 1);
    }

    @Override
    public ScoreV2 calculateAdditionalScore(ScoreV2 scoreV2) {
        return scoreV2.add(pin.getCount());
    }

    public String describe() {
        return "" + pin.getCount();
    }
}
