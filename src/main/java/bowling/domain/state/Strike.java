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
      return new Score(first.getCount(), 2);
    }

    return score.accumulate(first.getCount(), 2);
  }

  @Override
  public Score accumulate(Score score) {
    if (score.isStrike()) {
      return score.accumulate(first.getCount(), 1);
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
