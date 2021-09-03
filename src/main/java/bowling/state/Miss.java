package bowling.state;

import bowling.pin.Pin;
import bowling.score.Score;

public class Miss extends Finish {

  private static final String SEPARATOR = "|";

  private final Pin first;

  private final Pin second;

  public Miss(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String scoreMessage() {
    return first.toString() + SEPARATOR + second.toString();
  }

  @Override
  public Pin totalPin() {
    return second.totalDownPin(first);
  }

  @Override
  public Score score() {
    return Score.miss(totalPin().pinCount());
  }

  @Override
  public Score calculateScore(Score score) {
    score = first.sumScore(score);
    if(score.isFinishBallCount()){
      return score;
    }
    return second.sumScore(score);
  }
}
