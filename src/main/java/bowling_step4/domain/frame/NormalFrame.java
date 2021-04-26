package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;
import bowling_step4.domain.state.Ready;
import bowling_step4.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

  public static final int MAX_PLAY_COUNT = 8;
  public static final int MIN_PLAY_COUNT = 0;
  private static final String INVALID_PLAY_COUNT = "normal frame은 0번 이상 9번 미만 프레임만 가능합니다.";
  public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";

  private int playCount;
  private State state;
  private Frame nextFrame;

  public NormalFrame(int playCount) {
    validate(playCount);
    this.playCount = playCount;
    this.state = new Ready();
    this.nextFrame = next();
  }

  private void validate(int playCount) {
    if (playCount < MIN_PLAY_COUNT || playCount > MAX_PLAY_COUNT) {
      throw new IllegalArgumentException(INVALID_PLAY_COUNT);
    }
  }

  public static NormalFrame createFirst() {
    return new NormalFrame(MIN_PLAY_COUNT);
  }

  public Frame next() {
    if (playCount + 1 == 9) {
      return new FinalFrame();
    }
    return new NormalFrame(playCount + 1);
  }

  public State getState() {
    return state;
  }

  @Override
  public boolean isEnd() {
    return state.isFinish();
  }

  @Override
  public Frame play(Pin pinCount) {
    this.state = state.play(pinCount);

    if (state.isFinish()) {
      return this.nextFrame;
    }

    return this;
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {

    Score score = state.calculateAdditionalScore(beforeScore);

    if (score.isEndCalculate()) {
      return score;
    }
    return nextFrame.calculateAdditionalScore(score);

  }

  @Override
  public Score getScore() {
    if (!isEnd()) {
      return Score.ofUndefind();
    }

    Score totalScore = state.getScore();
    if (totalScore.isEndCalculate()) {
      return totalScore;
    }

    return nextFrame.calculateAdditionalScore(totalScore);

  }

  @Override
  public int getPlayCount() {
    return playCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalFrame frame = (NormalFrame) o;
    return playCount == frame.playCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(playCount);
  }

  @Override
  public String toString() {
    return "NormalFrame{" +
        "playCount=" + playCount +
        ", state=" + state.toString() +
        ", nextFrame=" + nextFrame +
        '}';
  }
}
