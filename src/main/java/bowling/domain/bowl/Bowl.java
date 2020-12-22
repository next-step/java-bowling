package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.state.State;

public interface Bowl {
    State stroke(Pins pins);
}
