package bowling.domain;

import java.util.List;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS;

    public static FrameStatus getStatus(List<DownedPin> downedPins) {
        if (FrameProgress.getStage(downedPins) == FrameProgress.START) {
            return MISS;
        }

        if (FrameProgress.getStage(downedPins) == FrameProgress.IN_PROGRESS
                && downedPins.get(0).isStrike()) {
            return STRIKE;
        }

        if (FrameProgress.getStage(downedPins) == FrameProgress.END
                && downedPins.get(0).isSpare(downedPins.get(1))) {
            return SPARE;
        }

        return MISS;
    }
}
