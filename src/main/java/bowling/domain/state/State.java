package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.turn.FallenPins;

public interface State {
  boolean isFinished();

  State bowl(FallenPins pins);

  String show();

  Score calculateScore();

  Score addScore(Score score);
}
