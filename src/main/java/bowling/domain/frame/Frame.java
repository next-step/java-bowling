package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.TotalResult;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;

public abstract class Frame {
  private static final int LAST_FRAME = 10;

  private final int round;

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

  public abstract TotalResult showFullResult();

  public abstract void addResult(TotalResult totalResult);

  public abstract Score score();

  protected abstract Score calculateAdditionalScore(Score score);
}
