package bowling.domain.frame;

import bowling.error.CannotMakeFrameException;

public class FrameFactory {
  private static final int FIRST_FRAME = 1;
  private static final int LAST_FRAME = 10;

  private FrameFactory(){

  }

  public static Frame of(int round) {
    if (round < FIRST_FRAME || round > LAST_FRAME) {
      throw new CannotMakeFrameException();
    }

    if (round == LAST_FRAME) {
      return new FinalFrame();
    }

    return new NormalFrame(round);
  }


}
