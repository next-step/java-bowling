package bowling;

import java.util.List;

public enum FrameResult {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static FrameResult fromShotResults(List<Integer> shots) {
        if (shots.get(0) == 10) {
            return STRIKE;
        }

        if (shots.stream().mapToInt(i -> i).sum() == 10) {
            return SPARE;
        }

        if (shots.stream().mapToInt(i -> i).sum() == 0) {
            return GUTTER;
        }

        return MISS;
    }
}
