package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

import static bowling.domain.Pin.MAX_COUNT;

public class FirstBowl extends State {

    private final Pin pin;

    public FirstBowl(Pin pin) {
        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {
        int sum = this.pin.getCount() + pin.getCount();
        if (sum > MAX_COUNT) {
            throw new IllegalArgumentException(MAX_COUNT - this.pin.getCount() + "이하의 숫자만 입력이 가능합니다.");
        }

        if (Pin.of(sum).isKnockDown()) {
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
}
