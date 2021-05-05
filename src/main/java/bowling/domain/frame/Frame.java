package bowling.domain.frame;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;
import bowling.error.CannotThrowBallException;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
  protected static final int MAX_THROWABLE_BALLS = 2;
  protected static final int STRIKE_SIZE = 1;
  protected static final int MAX_FALLEN_PINS = 10;
  protected static final int LAST_FRAME = 10;

  protected final int round;

  protected final List<FallenPins> fallenPins;

  protected Frame(int round) {
    fallenPins = new ArrayList<>();
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

  public FallenPins head() {
    return fallenPins.stream().findFirst().orElse(null);
  }

  public List<FallenPins> shot(FallenPins fallenPins) {
    checkThrowable(fallenPins);
    this.fallenPins.add(fallenPins);
    return this.fallenPins;
  }

  protected int fallenPinsStatus() {
    return fallenPins.stream()
      .mapToInt(fallenPins -> fallenPins.pins())
      .sum();
  }

  protected void checkThrowable(FallenPins pins) {
    if (fallenPins.size() >= MAX_THROWABLE_BALLS) {
      throw new CannotThrowBallException();
    }

    if (fallenPinsStatus() + pins.pins() > MAX_FALLEN_PINS) {
      throw new CannotThrowBallException();
    }
  }

  public Frame makeNextRound() {
    return of(round + 1);
  }

  public List<FallenPins> fallenPins() {
    return fallenPins;
  }

  public boolean isStrike() {
    if (fallenPins.size() != STRIKE_SIZE) {
      return false;
    }
    return head().isStrike();
  }

  public boolean isSpare() {
    return fallenPins.size() == MAX_THROWABLE_BALLS && fallenPinsStatus() == MAX_FALLEN_PINS;
  }

  public abstract boolean checkFinished();

  public abstract int round();
  public abstract String show();
}
