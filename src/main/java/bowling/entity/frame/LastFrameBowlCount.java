package bowling.entity.frame;

import java.util.Objects;

public class LastFrameBowlCount {
    private static final int MAX_BOWL_COUNT = 3;
    private static final int DEFAULT_BOWL_COUNT = 2;
    private static final int NOT_START_BOWL_COUNT = 0;

    private int maxBowlCount;
    private int currentBowlCount;

    public LastFrameBowlCount() {
        this.maxBowlCount = DEFAULT_BOWL_COUNT;
        this.currentBowlCount = NOT_START_BOWL_COUNT;
    }

    public void maxCountThree() {
        this.maxBowlCount = MAX_BOWL_COUNT;
    }

    public void bowl() {
        currentBowlCount++;
    }

    public boolean bowlingGameEnd() {
        return maxBowlCount == currentBowlCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrameBowlCount lastFrameBowlCount = (LastFrameBowlCount) o;
        return maxBowlCount == lastFrameBowlCount.maxBowlCount && currentBowlCount == lastFrameBowlCount.currentBowlCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxBowlCount, currentBowlCount);
    }
}
