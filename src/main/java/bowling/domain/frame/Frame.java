package bowling.domain.frame;

import bowling.domain.Pitch;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;


public interface Frame {
    void run(PitchNumberStrategy numberStrategy);

    Frame next();

    void addPitch(Pitch pitch);

    int no();

    int fallDownPinsCount();

    State state();

    boolean isFinal();

    boolean isEnd();

    boolean isThirdPitch();

    Score score();

    Score calculateBonusScore(Score beforeScore);

    void addState(State state);

    State currentState();
}
