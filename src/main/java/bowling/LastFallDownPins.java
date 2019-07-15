package bowling;

import java.util.ArrayList;
import java.util.List;

public class LastFallDownPins {

  private static final int LAST_FRAME_SPARE_AND_BONUS_SIZE = 3;
  private static final int LAST_FRAME_STRIKE_AND_BONUS_SIZE = 2;
  private static final int NO_BONUS_SIZE = 2;
  private static final int MAX_FALL_DOWN_COUNT = 10;
  private static final int FIRST_FALL_DOWN_COUNT_INDEX = 0;
  private static final int SECOND_FALL_DOWN_COUNT_INDEX = 1;
  private static final int SPARE_SIZE = 2;

  List<Integer> fallDownCounts = new ArrayList<>();

  public LastFallDownPins(int countOfPin) {
    fallDownCounts.add(countOfPin);
  }

  public static LastFallDownPins first(int countOfPin) {
    return new LastFallDownPins(countOfPin);
  }

  public LastFallDownPins roll(int countOfPin) {
    fallDownCounts.add(countOfPin);
    return this;
  }

  private boolean isStrike() {
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX) == MAX_FALL_DOWN_COUNT;
  }

  private boolean isSpare() {
    if (fallDownCounts.size() < SPARE_SIZE) {
      return false;
    }
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX) + fallDownCounts
        .get(SECOND_FALL_DOWN_COUNT_INDEX) == MAX_FALL_DOWN_COUNT;
  }

  private boolean isStrikeBonusSize() {
    return fallDownCounts.size() == LAST_FRAME_STRIKE_AND_BONUS_SIZE;
  }

  private boolean isSpareAndBonus() {
    return fallDownCounts.size() == LAST_FRAME_SPARE_AND_BONUS_SIZE;
  }

  private boolean isNoBonusSize() {
    return fallDownCounts.size() == NO_BONUS_SIZE;
  }

  public boolean isLastFrameFinish() {
    if (isSpareAndBonus()) {
      return true;
    }
    if (isStrikeAndBonus()) {
      return true;
    }
    if (isNoBonus()) {
      return true;
    }
    return false;
  }

  private boolean isNoBonus() {
    return !isStrike() && !isSpare() && isNoBonusSize();
  }

  private boolean isStrikeAndBonus() {
    return isStrike() && isStrikeBonusSize();
  }

}
