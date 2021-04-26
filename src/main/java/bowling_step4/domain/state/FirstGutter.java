package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public class FirstGutter implements State {

  @Override
  public State play(Pin fallenPin) {
    if (fallenPin.isGutter()) {
      return new SecondGutter(0);
    }
    if (fallenPin.isStrike()) {
      return new Spare(0, 10);
    }
    return new Miss(0, fallenPin.getCount());
  }

  @Override
  public boolean isFinish() {
    return false;
  }

  @Override
  public int getPitchCount() {
    return 1;
  }

  @Override
  public int getTotalCount() {
    return 0;
  }

  @Override
  public String toString() {
    return "-";
  }
  @Override
  public Score getScore() {
    return Score.ofUndefind();
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    return Score.ofUndefind();
  }
}
