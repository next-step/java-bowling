package bowling.domain;

import bowling.bowlingexception.InvalidProgressException;

import java.util.Arrays;
import java.util.List;

public enum FrameProgress {
    START(0),
    IN_PROGRESS(1),
    END(2);

    private final int stage;

    FrameProgress(int stage) {
        this.stage = stage;
    }

    public static FrameProgress getStage(List<DownedPin> downedPins) {
        return Arrays.stream(FrameProgress.values())
                .filter(progress -> progress.stage == downedPins.size())
                .findAny()
                .orElseThrow(InvalidProgressException::new);
    }
}
