package bowling;

import bowling.state.FirstRoll;
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
    if(new FallDownPin(fallDownCount).isStrike()) {
      return new Strike();
    }
    return new FirstRoll(fallDownCount);
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
