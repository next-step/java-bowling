package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public class Strike implements State  {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

  @Override
  public String toString() {
    return "X";
  }

  @Override
  public int getPitchCount() {
    return 1;
  }

  @Override
  public Score getScore() {
    return Score.ofStrike();
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    if (beforeScore.isEndCalculate()) {
      return beforeScore;
    }
    return beforeScore.play(10);
  }

  @Override
  public int getTotalCount() {
    return 10;
  }

  @Override
  public State play(Pin fallenPin) {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public boolean isFinish() {
    return true;
  }

}
