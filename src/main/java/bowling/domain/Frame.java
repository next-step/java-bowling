package bowling.domain;

import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

import java.util.List;

public interface Frame {
    void run(PitchNumberStrategy numberStrategy);

    Frame next();

    void addPitch(Pitch pitch);

    int no();

    List<Pitch> pitches();

    void changeState();

    State state();

    boolean isFinal();

    boolean isEnd();

    List<String> pitchResults();
}
