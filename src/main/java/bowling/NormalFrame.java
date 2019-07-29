package bowling;

import bowling.state.Ready;
import bowling.state.State;
import java.util.Objects;

public class NormalFrame implements Frame {

  private static final int FIRST_FRAME_NUMBER = 1;
  private static final int NEXT_FRAME_INTERVAL = 1;
  private static final int NORMAL_FRAME_LAST_INDEX = 9;

  private int frameNo;
  private State state;
  private Frame nextFrame;

  public NormalFrame(int frameNo) {
    this.frameNo = frameNo;
    this.state = new Ready();
  }

  public static Frame first() {
    return new NormalFrame(FIRST_FRAME_NUMBER);
  }

  public Frame roll(int countOfPin) {
    if (state instanceof Ready) {
      state = StateFactory.make(countOfPin);
      return this;
    }
    state = state.roll(countOfPin);
    return this;
  }

  public Frame nextFrame() {
    if (!isGameEnd()) {
      return this;
    }
    if (frameNo == NORMAL_FRAME_LAST_INDEX) {
      nextFrame = new LastFrame();
      return nextFrame;
    }
    nextFrame = new NormalFrame(frameNo + NEXT_FRAME_INTERVAL);
    return nextFrame;
  }

  @Override
  public int score() {
    Score score = state.score();
    if (score.hasNoAdditionalScore()) {
      return state.score().scoreValue();
    }
    return nextFrame.addScore(score);
  }

  @Override
  public int addScore(Score previousScore) {
    Score score = state.addScore(previousScore);
    if (score.hasNoAdditionalScore()) {
      return score.scoreValue();
    }
    if (nextFrame == null) {
      return Score.defaultScore().scoreValue();
    }
    return nextFrame.addScore(score);
  }

  public int getFrameNo() {
    return frameNo;
  }

  @Override
  public boolean isGameEnd() {
    if (state == null) {
      return false;
    }
    return state.isFinish();
  }

  @Override
  public String toString() {
    return state.toString();
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
    return frameNo == that.frameNo &&
        Objects.equals(state, that.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frameNo, state);
  }

}
