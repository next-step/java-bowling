package bowling.state;

import bowling.Pins;
import bowling.Score;
import java.util.Objects;

public class FirstBowl extends Running {

  private Pins firstPins;

  public FirstBowl(Pins pins) {
    this.firstPins = pins;
  }

  @Override
  public State bowl(Pins pins) {
    if (firstPins.isInvalidPins(pins)) {
      throw new IllegalArgumentException("한 프레임의 두 투구의 합은 10을 넘어갈 수 없습니다.");
    }
    if (firstPins.isSpare(pins)) {
      return new Spare(firstPins, pins);
    }
    return new Miss(firstPins, pins);
  }

  @Override
  public String desc() {
    return firstPins.desc();
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    return prevScore.addScore(new Score(firstPins.count(), 0));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirstBowl firstBowl = (FirstBowl) o;
    return Objects.equals(firstPins, firstBowl.firstPins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstPins);
  }
}
