package bowling.domain;

import static bowling.domain.BonusResult.NULL_RESULT;

import bowling.exception.CannotBowlException;

public class FinalFrame implements Frame {

  private final String SCORE_DELIMITER = "|";

  private RegularResult regularResult;
  private BonusResult bonusResult;

  public FinalFrame() {
    regularResult = new RegularResult();
    bonusResult = NULL_RESULT;
  }

  @Override
  public Frame roll(int pinCount) throws CannotBowlException {
    if (!regularResult.isFinished()) {
      regularResult.roll(pinCount);
      prepareBonusBowl();
      return this;
    }

    bonusResult.roll(pinCount);
    return this;
  }

  @Override
  public int getRound() {
    return Round.FINAL_ROUND;
  }

  @Override
  public Frame getNextFrame() {
    return NULL_FRAME;
  }

  private void prepareBonusBowl() {
    if (regularResult.isFinished()) {
      FrameState state = regularResult.getState();
      bonusResult = new BonusResult(state.getBonusBallCount());
    }
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    return null;
  }

  @Override
  public Score calculateScore() {
    return null;
  }

  @Override
  public String visualize() {
    String visualized = regularResult.visualize();
    String bonusVisualized = bonusResult.visualize();
    if (!bonusVisualized.isEmpty()) {
      visualized = String.join(SCORE_DELIMITER, visualized, bonusVisualized);
    }

    return visualized;
  }

  @Override
  public boolean isEnd() {
    return regularResult.isFinished() && bonusResult.isFinished();
  }
}
