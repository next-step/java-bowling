package bowling.domain.state;

import bowling.domain.Score;

public class Spare implements State {

  private Pins first;
  private Pins second;

  public Spare(Pins first, int second) {
    this.first = first;
    this.second = Pins.roll(second);
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    if (first.isGutter()) {
      return String.join(DELIMITER, GUTTER, SPARE);
    }

    return String.join(DELIMITER, first.toString(), SPARE);
  }

  @Override
  public int pins() {
    return first.getCount() + second.getCount();
  }

  @Override
  public boolean isDone() {
    return true;
  }

  @Override
  public Score score(Score score) {
    if (score == null) {
      return new Score(first.getCount() + second.getCount(), 1);
    }

    return score.accumulate(first.getCount() + second.getCount(), 1);
  }

  @Override
  public Score accumulate(Score score) {
    if (score.isStrike()) {
      return score.accumulate(first.getCount() + second.getCount());
    }

    if (score.isSpare()) {
      return score.accumulate(first.getCount());
    }

    return null;
  }

  @Override
  public String toString() {
    return "Spare{" + "first=" + first + ", second=" + second + '}';
  }
}
