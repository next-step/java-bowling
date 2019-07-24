package bowling;

import bowling.state.State;
import bowling.state.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FallDownPins implements State {

  private static final int FIRST_FALL_DOWN_COUNT_INDEX = 0;
  private static final int SECOND_FALL_DOWN_COUNT_INDEX = 1;
  private static final int NORMAL_FRAME_MAX_COUNT = 2;
  private static final int NO_PLAY_SIZE = 0;

  List<FallDownPin> fallDownCounts = new ArrayList<>();

  public State roll(int fallDownCount) {
    if (!validateSumOfFallDownCount(fallDownCount)) {
      throw new IllegalArgumentException("한 프레임에서 넘어뜨릴 수 있는 핀의 합은 10을 넘어 갈 수 없습니다.");
    }
    fallDownCounts.add(FallDownPin.of(fallDownCount));
    if (isStrike()) {
      return new Strike();
    }
    return this;
  }

  private boolean validateSumOfFallDownCount(int fallDownCount) {
    if (isNoPlay()) {
      return true;
    }
    return getFirstFallDown().isValidCount(fallDownCount);
  }

  private boolean isNoPlay() {
    return fallDownCounts.size() == NO_PLAY_SIZE;
  }

  public Boolean isFinish() {
    if (isNoPlay()) {
      return false;
    }
    if (isMaxCount()) {
      return true;
    }
    if (isStrike()) {
      return true;
    }
    return false;
  }

  private boolean isStrike() {
    return getFirstFallDown().isStrike();
  }

  private FallDownPin getFirstFallDown() {
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX);
  }

  private boolean isMaxCount() {
    return fallDownCounts.size() == NORMAL_FRAME_MAX_COUNT;
  }

  private FallDownPin getSecondFallDown() {
    return fallDownCounts.get(SECOND_FALL_DOWN_COUNT_INDEX);
  }

  private boolean isSpare() {
    if (fallDownCounts.size() < NORMAL_FRAME_MAX_COUNT) {
      return false;
    }
    return getFirstFallDown().isSpare(getSecondFallDown());
  }

  @Override
  public String toString() {
    if (isSpare()) {
      return getFirstFallDown().toString() + "|" + "/";
    }
    return fallDownCounts.stream().map(FallDownPin::toString).collect(Collectors.joining("|"));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FallDownPins that = (FallDownPins) o;
    return Objects.equals(fallDownCounts, that.fallDownCounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fallDownCounts);
  }
}
