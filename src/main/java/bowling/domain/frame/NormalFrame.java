package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotCalculateException;

public class NormalFrame implements Frame {
  private static final int NEXT_FRAME_COUNT = 1;

  private final int round;
  private Frame nextFrame;
  private State state;

  protected NormalFrame(int round) {
    this.round = round;
    this.state = Ready.of();
  }

  @Override
  public Frame bowl(FallenPins fallenPins) {
    state = state.bowl(fallenPins);
    if (state.isFinished()) {
      nextFrame = FrameFactory.of(round + NEXT_FRAME_COUNT);
      return nextFrame;
    }
    return this;
  }

  @Override
  public TotalResult showFullResult() {
    TotalResult totalResult = new TotalResult();

    addResult(totalResult);
    return totalResult;
  }

  @Override
  public void addResult(TotalResult totalResult) {
    FrameResult frameResult;

    try {
      frameResult = new FrameResult(show(), score().getScore(), true);
    } catch (CannotCalculateException cannotCalculateException) {
      frameResult = new FrameResult(show(), -1, false);
    }

    totalResult.add(frameResult);

    if (nextFrame != null) {
      nextFrame.addResult(totalResult);
    }

  }

  @Override
  public Score score() {
    Score score = state.calculateScore();
    if (score.canCalculateScore()) {
      return score;
    }
    return nextFrame.calculateAdditionalScore(score);
  }

  @Override
  public Score calculateAdditionalScore(Score score) {
    if (state.isFinished()) {
      score = state.addScore(score);
    }

    if (nextFrame == null) {
      return score;
    }

    return nextFrame.calculateAdditionalScore(score);
  }

  public int round() {
    return round;
  }

  @Override
  public boolean checkFinished() {
    return state.isFinished();
  }

  @Override
  public String show() {
    return state.show();
  }

}
