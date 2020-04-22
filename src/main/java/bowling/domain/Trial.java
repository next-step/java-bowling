package bowling.domain;

import bowling.exception.CannotBowlException;

public class Trial {

  private PinCount pinCount;
  private TrialState state;

  private Trial(TrialState state) {
    this.pinCount = new PinCount(0);
    this.state = state;
  }

  public int getPinCount() {
    return pinCount.getPinCount();
  }

  public static Trial initialize() {
    return new Trial(TrialState.NOT_PLAYED);
  }

  public static Trial block() {
    return new Trial(TrialState.NOT_ALLOWED);
  }

  public boolean isStrike() {
    return isPlayed() && pinCount.isStrike();
  }

  public boolean isSpare(Trial first) {
    return isPlayed() && !first.isStrike() && pinCount.isSpareOf(first.pinCount);
  }

  public boolean isPlayed() {
    return state == TrialState.PLAYED;
  }

  public boolean isNotPlayed() {
    return state == TrialState.NOT_PLAYED;
  }

  public boolean isGutter() {
    return isPlayed() && pinCount.isGutter();
  }

  public void roll(int pinCount) throws CannotBowlException {
    if (!isNotPlayed()) {
      throw new CannotBowlException("볼링을 칠 수 있는 상태가 아닙니다.");
    }

    this.pinCount = new PinCount(pinCount);
    this.state = TrialState.PLAYED;
  }

  public void validateSecondTrial(Trial first) throws CannotBowlException {
    pinCount.isValidSecondTrialOf(first.pinCount);
  }

  public void blockIfStrike(Trial first) {
    if (first.isStrike()) {
      state = TrialState.NOT_ALLOWED;
    }
  }

}
