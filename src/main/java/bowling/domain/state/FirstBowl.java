package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class FirstBowl extends State {

    private static final int MAX_COUNT = 10;

    private final Pin pin;

    public FirstBowl(Pin pin) {

        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {

        int sum = this.pin.count() + pin.count();
        if (sum > MAX_COUNT) {
            throw new IllegalArgumentException(MAX_COUNT - this.pin.count() + "이하의 숫자만 입력이 가능합니다.");
        }

        if (new Pin(sum).isKnockDown()) {
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

        return new Score(pin.count(), 1);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {

        return score.add(pin.count());
    }

    @Override
    public List<Pin> pins() {

        return List.of(pin);
    }
}
