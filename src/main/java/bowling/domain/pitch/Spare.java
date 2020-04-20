package bowling.domain.pitch;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

import java.util.Optional;

class Spare implements State {
    @Override public State bowl(Pin pin) {
        return Ready.bowlFirst(pin);
    }

    @Override public Optional<Score> calculateBonusScore(Frame next) {
        return next.getScoreForOnePitch();
    }
}
