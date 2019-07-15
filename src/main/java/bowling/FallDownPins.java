package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FallDownPins {

  private static final int MAX_FALL_DOWN_COUNT = 10;
  private static final int MIN_FALL_DOWN_COUNT = 0;
  private static final int FIRST_FALL_DOWN_COUNT_INDEX = 0;
  private static final int NORMAL_FRAME_ROLL_CHANCE = 2;

  List<Integer> fallDownCounts = new ArrayList<>();


  public FallDownPins(int fallDownCount) {
    validatePinCount(fallDownCount);
    fallDownCounts.add(fallDownCount);
  }

  private void validatePinCount(int fallDownCount) {
    if (fallDownCount > MAX_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀은 한번에 " + MAX_FALL_DOWN_COUNT + "개 초과로 넘어가지 못합니다.");
    }
    if (fallDownCount < MIN_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀이 넘어간 수는 음수가 될 수 없습니다.");
    }
  }

  public static FallDownPins first(int fallDownCount) {
    return new FallDownPins(fallDownCount);
  }

  public FallDownPins second(int fallDownCount) {
    if(validateSumOfFallDownCount(fallDownCount)) {
      throw new IllegalArgumentException("한 프레임에서 넘어뜨릴 수 있는 핀의 합은 10을 넘어 갈 수 없습니다.");
    }
    validatePinCount(fallDownCount);
    fallDownCounts.add(fallDownCount);
    return this;
  }

  private boolean validateSumOfFallDownCount(int fallDownCount) {
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX) + fallDownCount > MAX_FALL_DOWN_COUNT;
  }

  public boolean isFinish() {
    if (isAllFallDown()) {
      return true;
    }
    if (isCompleteTwice()) {
      return true;
    }
    return false;
  }

  private boolean isCompleteTwice() {
    return fallDownCounts.size() == NORMAL_FRAME_ROLL_CHANCE;
  }

  private boolean isAllFallDown() {
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX) == MAX_FALL_DOWN_COUNT;
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
