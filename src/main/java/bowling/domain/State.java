package bowling.domain;

import bowling.domain.state.Hold;
import bowling.domain.state.Strike;

import java.util.List;

public interface State {
    static State from(int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Hold(pin);
    }

    State roll(int count);

    List<String> getValue();

    Score getScore();

    default boolean isFinish() {
        return false;
    }

    default Score sumScore(Score before) {
        if (before.canNextSum()) {
            before.sum(getScore());
        }
        return before;
    }
}