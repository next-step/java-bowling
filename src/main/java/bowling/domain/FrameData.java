package bowling.domain;

import bowling.domain.bowlresult.RegularResult;
import bowling.exception.CannotBowlException;

public class FrameData {

  private RegularResult regularResult;
  private Round round;

  public FrameData(Round round) {
    this.regularResult = new RegularResult();
    this.round = round;
  }

  public RegularResult getRegularResult() {
    return regularResult;
  }

  public int getRolledBowlCount() {
    return regularResult.getRolledBowlCount();
  }

  public Score getScore() {
    return regularResult.getScoreAll();
  }

  public void roll(int pinCount) throws CannotBowlException {
    regularResult.roll(pinCount);
  }

  public Round nextRound() {
    return round.next();
  }

  public boolean isFinished() {
    return regularResult.isFinished();
  }
}
