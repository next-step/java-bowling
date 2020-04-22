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

  public static FrameState of(BowlResult result) {
    Trial first = result.getFirst();
    Trial second = result.getSecond();

    if (first.isMaxPin()) {
      return FrameState.STRIKE;
    }

    if (second.isSpareOf(first)) {
      return FrameState.SPARE;
    }

    if (result.isFinished()) {
      return FrameState.MISS;
    }

    return FrameState.IN_PROGRESS;
  }
}
