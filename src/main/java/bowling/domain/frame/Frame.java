package bowling.domain.frame;

import bowling.domain.bowlresult.RegularResult;
import bowling.domain.Round;
import bowling.domain.Score;
import bowling.exception.CannotBowlException;

public class Frame {

  private RegularResult regularResult;
  private Round round;

  public Frame(Round round) {
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
