package bowling.domain;

public enum FrameState {
  IN_PROGRESS(0),
  MISS(0),
  SPARE(1),
  STRIKE(2);

  private int bonusBallCount;

  FrameState(int bonusBallCount) {
    this.bonusBallCount = bonusBallCount;
  }

  public int getBonusBallCount() {
    return bonusBallCount;
  }
}
