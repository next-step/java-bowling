package bowling.domain.frame;

import bowling.bowlingexception.InvalidProgressException;

import java.util.Arrays;
import java.util.List;

public enum NormalFrameProgress {
    ON_FIRST_PITCH(0),
    ON_SECOND_PITCH(1),
    END(2);

    private final int stage;

    NormalFrameProgress(int stage) {
        this.stage = stage;
    }

    public static NormalFrameProgress getProgress(List<DownedPin> downedPins) {
        return Arrays.stream(NormalFrameProgress.values())
                .filter(progress -> progress.stage == downedPins.size())
                .findAny()
                .orElseThrow(InvalidProgressException::new);
    }
}
