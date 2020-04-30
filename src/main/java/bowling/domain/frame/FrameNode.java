package bowling.domain.frame;

import bowling.domain.bowlresult.BonusResult;
import bowling.domain.bowlresult.RegularResult;
import bowling.domain.Round;
import bowling.domain.Score;
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

  RegularResult getRegularResult();

  BonusResult getBonusResult();

  FrameNode getNextFrame();

  FrameNode roll(int pinCount) throws CannotBowlException;

  Score calculateBonusScore(int bonusBowlCount);

  Score calculateScore();

  boolean isFinished();

  boolean isFinalFrame();
}
