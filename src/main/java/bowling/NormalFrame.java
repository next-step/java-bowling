package bowling;

import bowling.state.Ready;
import bowling.state.State;
import java.util.Objects;

public class NormalFrame implements Frame {

  private static final int LAST_NORMAL_FRAME_NO = 9;
  private static final int NEXT_FRAME_DISTANCE = 1;

  private State state;
  private Frame nextFrame;
  private int frameNo;

  public NormalFrame(int frameNo) {
    this.frameNo = frameNo;
    this.state = new Ready();
  }

  public static Frame first() {
    return new NormalFrame(1);
  }

  @Override
  public int frameNo() {
    return this.frameNo;
  }

  @Override
  public String desc() {
    return state.desc();
  }

  @Override
  public Frame bowl(Pins pins) {
    state = state.bowl(pins);
    if (state.isFinish()) {
      return nextFrame();
    }
    return this;
  }

  private Frame nextFrame() {
    if (frameNo == LAST_NORMAL_FRAME_NO) {
      nextFrame = new LastFrame();
      return nextFrame;
    }
    nextFrame = new NormalFrame(frameNo + NEXT_FRAME_DISTANCE);
    return nextFrame;
  }

  @Override
  public boolean isGameEnd() {
    return false;
  }

  @Override
  public Score getScore() {
    Score score = state.getScore();
    if (score.isCompleteScore()) {
      return score;
    }
    if (nextFrame == null) {
      return Score.noFinishScore();
    }
    return nextFrame.addAdditionalScore(score);
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    Score score = state.addAdditionalScore(prevScore);
    if (score.isCompleteScore()) {
      return score;
    }
    if (nextFrame == null) {
      return Score.noFinishScore();
    }
    return nextFrame.addAdditionalScore(score);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalFrame that = (NormalFrame) o;
    return frameNo == that.frameNo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(frameNo);
  }
}
