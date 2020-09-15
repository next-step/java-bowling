package bowling.domain.state;

import bowling.domain.Score;

public class Open implements State {

  private Pins first;
  private Pins second;

  public Open(Pins first, int second) {
    this.first = first;
    this.second = Pins.roll(second);
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    if (isDoubleGutter()) {
      return String.join(DELIMITER, GUTTER, GUTTER);
    }

    if (first.isGutter()) {
      return String.join(DELIMITER, GUTTER, second.toString());
    }

    if (second.isGutter()) {
      return String.join(DELIMITER, first.toString(), GUTTER);
    }

    return String.join(DELIMITER, first.toString(), second.toString());
  }

  private boolean isDoubleGutter() {
    return second.isGutter() && first.isGutter();
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
      return new Score(first.getCount() + second.getCount());
    }

    return score.accumulate(first.getCount() + second.getCount());
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
    return "Open{" + "first=" + first + ", second=" + second + '}';
  }
}
