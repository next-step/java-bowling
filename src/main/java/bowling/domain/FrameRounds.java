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

    public FrameRounds() {
        this.status = RoundsStatus.NONE;
        this.frameRounds = new ArrayList<>();
    }

    public void play(int clearCount) {
        int roundIndex = this.frameRounds.size();
        frameRounds.add(new FrameRound(roundIndex, clearCount));

        if (this.status.isNone()) {
            this.status = RoundsStatus.getRoundStatus(roundIndex, totalClearPinCount());
        }
    }

    public boolean isEnd(boolean isLastFrame) {
        if (isLastFrame
                && (this.status.isStrike() || this.status.isSpare())
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
