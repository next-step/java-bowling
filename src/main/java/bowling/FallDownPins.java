package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FallDownPins {

  private static final int MAX_FALL_DOWN_COUNT = 10;
  private static final int MIN_FALL_DOWN_COUNT = 0;

  List<Integer> fallDownCounts = new ArrayList<>();


  public FallDownPins(int fallDownCount) {
    if (fallDownCount > MAX_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀은 한번에 "+MAX_FALL_DOWN_COUNT+"개 초과로 넘어가지 못합니다.");
    }
    if (fallDownCount < MIN_FALL_DOWN_COUNT) {
      throw new IllegalArgumentException("핀이 넘어간 수는 음수가 될 수 없습니다.");
    }

  }

  public static FallDownPins first(int fallDownCount) {
    return new FallDownPins(fallDownCount);
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
