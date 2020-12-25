package bowling.domain;

import bowling.bowlingexception.InvalidProgressException;

import java.util.Arrays;
import java.util.List;

public enum FrameProgress {
    ON_FIRST_PITCH(0),
    ON_SECOND_PITCH(1),
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
