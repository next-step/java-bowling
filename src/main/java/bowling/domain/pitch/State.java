package bowling.domain.pitch;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

import java.util.Optional;

interface State {
    State bowl(Pin pin);

    Optional<Score> calculateBonusScore(Frame next);
}
