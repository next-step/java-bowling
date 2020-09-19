package bowling.domain.state;

import bowling.domain.Score;

public class Pitching implements State {

  private Pins first;

  public Pitching(Pins first) {
    this.first = first;
  }

  @Override
  public State roll(int second) {
    if (first.isSpare(second)) {
      return new Spare(first, second);
    }
    return new Open(first, second);
  }

  @Override
  public String symbol() {
    if (first.isGutter()) {
      return GUTTER;
    }
    return first.toString();
  }

  @Override
  public int pins() {
    return first.getCount();
  }

  @Override
  public Score score(Score score) {
    return null;
  }

  @Override
  public Score accumulate(Score score) {
    if (score.isSpare()) {
      return score.accumulate(first.getCount());
    }

    return null;
  }

  @Override
  public String toString() {
    return "Pitching{" + "first=" + first + '}';
  }
}