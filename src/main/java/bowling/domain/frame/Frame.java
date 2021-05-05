package bowling.domain.frame;

import bowling.domain.Result;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;

public abstract class Frame {
  protected static final int LAST_FRAME = 10;

  protected final int round;

  protected Frame(int round) {
    this.round = round;
  }

  public static Frame of(int round) {
    if (round < 1 || round > LAST_FRAME) {
      throw new CannotMakeFrameException();
    }

    if (round == LAST_FRAME) {
      return new FinalFrame(round);
    }

    return new NormalFrame(round);
  }
  public int round() {
    return round;
  }

  public abstract boolean checkFinished();
  public abstract String show();
  public abstract Frame bowl(FallenPins fallenPins);
  public abstract Result showFullResult();
  public abstract void addResult(Result result);
}
