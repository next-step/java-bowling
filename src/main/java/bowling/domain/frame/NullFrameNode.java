package bowling.domain.frame;

import bowling.domain.bowlresult.BonusResult;
import bowling.domain.bowlresult.RegularResult;
import bowling.domain.Round;
import bowling.domain.Score;
import bowling.exception.CannotBowlException;

public class NullFrameNode implements FrameNode {

  private Round round;

  private NullFrameNode(Round round) {
    this.round = round;
  }

  public static NullFrameNode of(Round round) {
    return new NullFrameNode(round);
  }

  @Override
  public int getRolledBowlCount() {
    return 0;
  }

  @Override
  public RegularResult getRegularResult() {
    return RegularResult.NULL_RESULT;
  }

  @Override
  public BonusResult getBonusResult() {
    return BonusResult.NULL_RESULT;
  }

  @Override
  public FrameNode getNextFrame() {
    return NullFrameNode.of(round.next());
  }

  @Override
  public FrameNode roll(int pinCount) throws CannotBowlException {
    throw new CannotBowlException("유효하지 않은 프레임입니다.");
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    if (bonusBowlCount == NO_BONUS_BOWL) {
      return Score.zero();
    }

    return Score.ofNull();
  }

  @Override
  public Score calculateScore() {
    return Score.ofNull();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean isFinalFrame() {
    return round.isFinal();
  }
}
