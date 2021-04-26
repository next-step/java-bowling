package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;
import org.apache.logging.log4j.util.Strings;

public class Ready implements State {

  @Override
  public State play(Pin fallenPin) {
    if (fallenPin.isGutter()) {
      return new FirstGutter();
    }

    if (fallenPin.isStrike()) {
      return new Strike();
    }
    return new FirstBowl(fallenPin);
  }

  @Override
  public int getPitchCount() {
    return 0;
  }

  @Override
  public int getTotalCount() {
    return 0;
  }

  @Override
  public String toString() {
    return Strings.EMPTY;
  }

  @Override
  public boolean isFinish() {
    return false;
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
