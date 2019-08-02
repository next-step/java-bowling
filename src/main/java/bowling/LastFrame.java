package bowling;

import bowling.state.LastFrameState;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;
  private static final int LAST_FRAME_MAX_ROLL_COUNT = 3;

  private LastFrameState states;
  private int bowlCount = 0;

  public LastFrame() {
    states = new LastFrameState();
  }

  @Override
  public int frameNo() {
    return LAST_FRAME_NO;
  }

  @Override
  public String desc() {
    return states.desc();
  }

  @Override
  public Frame bowl(Pins pins) {
    if (isGameEnd()) {
      throw new RuntimeException("게임오버");
    }
    bowlCount++;
    states = states.bowl(pins);
    return this;
  }

  @Override
  public boolean isGameEnd() {
    return states.isMiss() || bowlCount == LAST_FRAME_MAX_ROLL_COUNT;
  }

  @Override
  public Score getScore() {
    if (!isGameEnd()) {
      return Score.noFinishScore();
    }
    return new Score(states.getScore(), 0);
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    return states.addAdditionalScore(prevScore);
  }
}
