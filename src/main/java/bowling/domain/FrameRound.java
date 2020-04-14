package bowling.domain;

import lombok.Getter;

public class FrameRound {
    @Getter
    private int roundIndex;
    @Getter
    private int clearPinCount;

    public FrameRound(int roundIndex, int clearPinCount) {
        this.roundIndex = roundIndex;
        this.clearPinCount = clearPinCount;
    }
}
