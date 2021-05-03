package bowling.domain.turn;

import java.util.Objects;

public class BallRelease {
  private final FallenPins fallenPins;

  public BallRelease(FallenPins fallenPins) {
    this.fallenPins = fallenPins;
  }

  public boolean checkAddable(FallenPins fallenPins) {
    return this.fallenPins.checkAddable(fallenPins);
  }

  public FallenPins fallenPins() {
    return fallenPins;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BallRelease that = (BallRelease) o;
    return Objects.equals(fallenPins, that.fallenPins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fallenPins);
  }

  public boolean isStrike() {
    return fallenPins.isStrike();
  }
}
