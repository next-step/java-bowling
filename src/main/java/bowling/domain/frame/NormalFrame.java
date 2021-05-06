package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.domain.turn.FallenPins;

public class NormalFrame extends Frame {
  private static final int NEXT_FRAME_COUNT = 1;

  private Frame nextFrame;
  private State state;

  protected NormalFrame(int round) {
    super(round);
    this.state = Ready.of();
  }

  @Override
  public Frame bowl(FallenPins fallenPins) {
    state = state.bowl(fallenPins);
    if(state.isFinished()) {
      nextFrame = Frame.of(round+NEXT_FRAME_COUNT);
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

  public void addResult(TotalResult totalResult){
    boolean scoreVisible = state.isFinished();
    FrameResult frameResult = new FrameResult(show(), score().getScore(), scoreVisible);

    totalResult.add(frameResult);

    if(nextFrame != null){
      nextFrame.addResult(totalResult);
    }

  }

  @Override
  // TODO
  public Score score() {
    Score score = state.calculateScore();

    if(score.canCalculateScore()){
      return nextFrame.calculateAdditionalScore(score);
    }
    return state.calculateScore();
  }

  @Override
  // TODO
  protected Score calculateAdditionalScore(Score score) {
    return null;
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
