package bowling.domain;

import bowling.exception.CannotBowlException;

public class NullFrame implements Frame {

  private Round round;

  private NullFrame(Round round) {
    this.round = round;
  }

  public static NullFrame of(Round round) {
    return new NullFrame(round);
  }

  @Override
  public int getRound() {
    return round.getRound();
  }

  @Override
  public int getRolledBowlCount() {
    return 0;
  }

  @Override
  public Frame getNextFrame() {
    return NullFrame.of(round.next());
  }

  @Override
  public Frame roll(int pinCount) throws CannotBowlException {
    throw new CannotBowlException("유효하지 않은 프레임입니다.");
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    if (bonusBowlCount == 0) {
      return Score.zero();
    }

    return Score.ofNull();
  }

  @Override
  public Score calculateScore() {
    return Score.ofNull();
  }

  @Override
  public boolean isEnd() {
    return false;
  }

  @Override
  public boolean isFinalFrame() {
    return round.isFinal();
  }
}
