package bowling.model;

public enum FrameResult {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static FrameResult fromShotResults(ShotResults shotResults) {
        if (shotResults.firstIsMax()) {
            return STRIKE;
        }

        if (shotResults.sumIsMax()) {
            return SPARE;
        }

        if (shotResults.sumIsMin()) {
            return GUTTER;
        }

        return MISS;
    }
}
