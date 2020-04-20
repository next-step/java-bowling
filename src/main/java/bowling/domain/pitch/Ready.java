package bowling.domain.pitch;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

import java.util.Optional;

class Ready implements State {
    public static State bowlFirst(Pin pin) {
        if (pin.isMax()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override public State bowl(Pin pin) {
        return bowlFirst(pin);
    }

    @Override public Optional<Score> calculateBonusScore(Frame next) {
        return Optional.empty();
    }
}
