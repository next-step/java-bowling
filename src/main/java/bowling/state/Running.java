package bowling.state;

import bowling.Score;

public abstract class Running implements State {

  @Override
  public boolean isFinish() {
    return Boolean.FALSE;
  }

  @Override
  public Score getScore() {
    return Score.noFinishScore();
  }

}
