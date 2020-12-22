package bowling.domain;

public enum FrameStatus {
    STRIKE,
    GUTTER,
    SPARE,
    MISS;

    public static FrameStatus getStatus(DownedPin previousPitch, DownedPin currentPitch) {
        if (previousPitch.isStrike()) {
            return STRIKE;
        }

        return MISS;
    }
}
