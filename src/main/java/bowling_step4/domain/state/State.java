package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public interface State {

  State play(Pin fallenPin);

  boolean isFinish();

  int getPitchCount();

  int getTotalCount();

  Score getScore();

  Score calculateAdditionalScore(Score beforeScore);
}
