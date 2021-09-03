package bowling.state;

import bowling.score.Score;

public abstract class Running implements State {

  @Override
  public boolean isFinish() {
    return false;
  }

  @Override
  public Score score() {
    throw new RuntimeException();
  }
}