package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FrameRounds {
    @Getter
    private RoundsStatus status;
    @Getter
    private List<FrameRound> frameRounds;

    private FrameRounds(List<FrameRound> frameRounds, RoundsStatus status) {
        this.frameRounds = frameRounds;
        this.status = status;
    }

    public void play(int clearCount) {
        frameRounds.add(new FrameRound(this.frameRounds.size(), clearCount));

        this.status = RoundsStatus.getRoundStatus(totalClearPinCount());
    }

    public static FrameRounds of(int clearPinCount) {
        List<FrameRound> frameRounds = new ArrayList<>();
        frameRounds.add(new FrameRound(0, clearPinCount));

        RoundsStatus status = RoundsStatus.firstRoundStatus(clearPinCount);
        return new FrameRounds(frameRounds, status);
    }

    public boolean isEnd() {
        return this.status != RoundsStatus.NONE;
    }

    private int totalClearPinCount() {
        return frameRounds.stream()
                .mapToInt(FrameRound::getClearPinCount)
                .sum();
    }
}
