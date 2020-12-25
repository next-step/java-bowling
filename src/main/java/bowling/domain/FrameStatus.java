package bowling.domain;

import java.util.List;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS;

    public static FrameStatus getStatus(List<DownedPin> downedPins) {
        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.ON_FIRST_PITCH) {
            return MISS;
        }

        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.ON_SECOND_PITCH
                && downedPins.get(0).isStrike()) {
            return STRIKE;
        }

        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.END
                && downedPins.get(0).isSpare(downedPins.get(1))) {
            return SPARE;
        }

        return MISS;
    }
}
