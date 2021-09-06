package bowling.state;

import bowling.pin.Pin;
import bowling.score.Score;

public class Spare extends Finish {

  private static final String SEPARATOR = "|";
  private static final String SPARE = "/";

  private final Pin first;

  private final Pin second;

  public Spare(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String scoreMessage() {
    return first.toString() + SEPARATOR + SPARE;
  }

  @Override
  public Pin totalPin() {
    return second.totalDownPin(first);
  }

  @Override
  public Score score() {
    return Score.spare();
  }

  @Override
  public Score calculateScore(Score score) {
    score = first.sumScore(score);
    if (score.isFinishBallCount()) {
      return score;
    }
    return second.sumScore(score);
  }
}
