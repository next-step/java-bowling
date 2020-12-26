package bowling.domain.frame;

import bowling.bowlingexception.InvalidProgressException;

import java.util.Arrays;
import java.util.List;

public enum LastFrameProgress {

    ON_FIRST_PITCH(0),
    ON_SECOND_PITCH(1),
    ON_ADDITIONAL_PITCH(2),
    END(3);

    private final int stage;

    LastFrameProgress(int stage) {
        this.stage = stage;
    }

    public static LastFrameProgress getProgress(List<NormalFrame> downedPins) {
        return Arrays.stream(LastFrameProgress.values())
                .filter(progress -> progress.stage == countsOfTotalPitch(downedPins))
                .findFirst()
                .orElseThrow(InvalidProgressException::new);
    }

    public static int countsOfTotalPitch(List<NormalFrame> downedPins) {
        return downedPins.stream()
                .map(Frame::getNumThrown)
                .reduce(0, Integer::sum);
    }
}
