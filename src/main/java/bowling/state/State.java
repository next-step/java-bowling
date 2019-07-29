package bowling.state;

import bowling.Score;

public interface State {

  State roll(int countOfPin);

  Boolean isFinish();

  Score score();

  Score addScore(Score previousScore);
}
