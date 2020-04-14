package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FrameRounds {
    private static final int MAX_ROUND_SIZE = 3;
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

        if (RoundsStatus.isNone(this.status)) {
            this.status = RoundsStatus.getRoundStatus(totalClearPinCount());
        }
    }

    public static FrameRounds of(int clearPinCount) {
        List<FrameRound> frameRounds = new ArrayList<>();
        frameRounds.add(new FrameRound(0, clearPinCount));

        RoundsStatus status = RoundsStatus.firstRoundStatus(clearPinCount);
        return new FrameRounds(frameRounds, status);
    }

    public boolean isEnd(boolean isLastFrame) {
        if (isLastFrame
                && (RoundsStatus.isStrike(this.status) || RoundsStatus.isSpare(this.status))
                && !isMaxRoundSize(this.frameRounds.size())) {
            return false;
        }

        return this.status != RoundsStatus.NONE;
    }

    private int totalClearPinCount() {
        return frameRounds.stream()
                .mapToInt(FrameRound::getClearPinCount)
                .sum();
    }

    private boolean isMaxRoundSize(int size) {
        return size == MAX_ROUND_SIZE;
    }
}
