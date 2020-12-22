package bowling.domain;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS;

    public static FrameStatus getStatus(DownedPin previousPitch, DownedPin currentPitch) {
        if (previousPitch.isStrike()) {
            return STRIKE;
        }

        if (previousPitch.isSpare(currentPitch)) {
            return SPARE;
        }

        return MISS;
    }
}
