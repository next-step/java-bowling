package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class FirstBowl extends State {

    private static final int MAX_SCORE = 10;
    private static final int LEFT_CHANCE = 1;

    private final Pin pin;

    public FirstBowl(Pin pin) {

        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {

        int sum = this.pin.count() + pin.count();
        if (sum > MAX_SCORE) {
            throw new IllegalArgumentException(MAX_SCORE - this.pin.count() + "이하의 숫자만 입력이 가능합니다.");
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

        return new Score(pin.count(), LEFT_CHANCE);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {

        return score.add(pin.count());
    }

    @Override
    public List<Pin> pins() {

        return List.of(pin);
    }

    @Override
    public boolean hasPins(final int size) {

        return pins().size() == size;
    }
}
