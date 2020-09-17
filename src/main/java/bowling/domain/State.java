package bowling.domain;

import bowling.domain.state.*;

import java.util.List;

public interface State {
    static State from(int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Hold(pin);
    }

    static State last(State current, int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return Final.from(current, count);
    }

    State roll(int count);

    List<String> toValues();

    Score getScore();

    default boolean isFinish() {
        return false;
    }

    default Score sumScore(Score before) {
        if (before.canNextSum()) {
            getScore().sum(before);
        }
        return before;
    }
}