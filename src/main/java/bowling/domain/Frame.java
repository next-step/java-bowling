package bowling.domain;

import bowling.exception.CannotBowlException;

public interface Frame {

  static Frame of(Round round) {
    if (round.isFinal()) {
      return new FinalFrame();
    }

    return NormalFrame.of(round);
  }

  int getRound();

  int getRolledBowlCount();

  Frame getNextFrame();

  Frame roll(int pinCount) throws CannotBowlException;

  Score calculateBonusScore(int bonusBowlCount);

  Score calculateScore();

  boolean isEnd();

  boolean isFinalFrame();
}
