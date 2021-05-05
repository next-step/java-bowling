package bowling.domain.frame;

import bowling.domain.Result;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.domain.turn.FallenPins;

import java.util.ArrayList;
import java.util.List;

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
  public Result showFullResult() {
    Result result = new Result();

    addResult(result);
    return result;
  }

  public void addResult(Result result){
    result.add(state.show());

    if(nextFrame != null){
      nextFrame.addResult(result);
    }

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
