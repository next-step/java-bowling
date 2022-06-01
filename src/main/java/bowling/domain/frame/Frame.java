package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public interface Frame {

    Frame bowling(int pins);

    Frame next(int pins);

    int round();

    boolean isFinalFrame();

    boolean isFinishBowling();

    boolean isPrinting();

    String result();

    State state();

    int score();

    int nextScore(Score before);
}
