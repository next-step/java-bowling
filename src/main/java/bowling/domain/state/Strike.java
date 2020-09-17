package bowling.domain.state;

import bowling.domain.Score;

public class Strike implements State {

  private Pins first;

  public Strike(Pins first) {
    this.first = first;
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    return STRIKE;
  }

  @Override
  public int pins() {
    return first.MAX_PINS;
  }

  @Override
  public boolean isDone() {
    return true;
  }

  @Override
  public Score score(Score score) {
    if (score == null) {
      return Score.strike(first.getCount());
    }

    return score.accumulateTwice(first.getCount());
  }

  @Override
  public Score accumulate(Score score) {
    if (score.isStrike()) {
      return score.accumulateOnce(first.getCount());
    }

    if (score.isSpare()) {
      return score.accumulate(first.getCount());
    }

    return null;
  }

  @Override
  public String toString() {
    return "Strike{" + "first=" + first + '}';
  }
}
