package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;


public interface Frame {
    void run(PitchNumberStrategy numberStrategy);

    Frame next();

    void addPitch(Pitch pitch);

    int no();

    int currentFallDownPinsCount();

    State state();

    boolean isFinal();

    boolean isEnd();

    Score score();

    Score calculateBonusScore(Score beforeScore);

    String symbol();
}
