package bowling.domain;

import bowling.domain.bowlresult.BonusResult;
import bowling.domain.bowlresult.RegularResult;

public class FrameResult {

  private RegularResult regularResult;
  private BonusResult bonusResult;
  private Score score;

  public FrameResult(RegularResult regularResult, BonusResult bonusResult, Score score) {
    this.regularResult = regularResult;
    this.bonusResult = bonusResult;
    this.score = score;
  }

  public RegularResult getRegularResult() {
    return regularResult;
  }

  public BonusResult getBonusResult() {
    return bonusResult;
  }

  public Score getScore() {
    return score;
  }
}
