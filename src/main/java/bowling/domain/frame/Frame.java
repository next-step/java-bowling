package bowling.domain.frame;

import bowling.domain.Pitch;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

import java.util.List;

public interface Frame {
    void run(PitchNumberStrategy numberStrategy);

    Frame next();

    void addPitch(Pitch pitch);

    int no();

    int fallDownPinsCount();

    State state();

    boolean isFinal();

    boolean isEnd();

    List<String> pitchResults();

    boolean isThirdPitch();
}
