package bowling.model;

public enum FrameResult {
    STRIKE,
    SPARE,
    MISS;

    public static FrameResult fromShotResults(ShotResults shotResults) {
        if (shotResults.empty()) {
            return MISS;
        }

        if (shotResults.firstIsMax()) {
            return STRIKE;
        }

        if (shotResults.sumIsMax()) {
            return SPARE;
        }

        return MISS;
    }
}
