package bowling.domain.state;

import bowling.domain.Pitch;
import bowling.domain.Score;
import bowling.domain.frame.Frame;

public interface State {

    State run(Pitch pitch, Frame frame);

    boolean progressing();

    Score score();

    Score calculateBonusScore(Score beforeScore);

    String symbol();
}
