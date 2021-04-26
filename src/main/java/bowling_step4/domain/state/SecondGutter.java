package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public class SecondGutter implements State {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";
  private final Pin firstPin;

  public SecondGutter(int firstPin) {
    this(new Pin(firstPin));
  }

  public SecondGutter(Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State play(Pin fallenPin) {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public boolean isFinish() {
    return true;
  }

  @Override
  public int getPitchCount() {
    return 2;
  }

  @Override
  public Score getScore() {
    return Score.ofMiss(firstPin.getCount());
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    beforeScore = firstPin.sumScore(beforeScore);
    if (beforeScore.isEndCalculate()) {
      return beforeScore;
    }
    return beforeScore.play(0);
  }

  @Override
  public int getTotalCount() {
    return firstPin.getCount();
  }

  @Override
  public String toString() {

    if (firstPin.isGutter()) {
      return "-|-";
    }

    return firstPin.toString() + "|-";
  }

}
