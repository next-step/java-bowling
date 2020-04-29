package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.CannotBowlException;

public interface FrameNode {
  int NO_BONUS_BOWL = 0;
  int MAX_BONUS_BOWL = 2;

  static FrameNode of(Round round) {
    if (round.isFinal()) {
      return new FinalFrame();
    }

    return NormalFrame.of(round);
  }

  int getRolledBowlCount();

  FrameNode getNextFrame();

  FrameNode roll(int pinCount) throws CannotBowlException;

  Score calculateBonusScore(int bonusBowlCount);

  Score calculateScore();

  boolean isFinished();

  boolean isFinalFrame();
}
