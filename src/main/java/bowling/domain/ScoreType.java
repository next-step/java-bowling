package bowling.domain;

public enum ScoreType {
    STRIKE(true),
    SPARE(true),
    MISS(false),
    GUTTER(false);

    private final boolean isFinished;

    ScoreType(boolean isFinished) {
        this.isFinished = isFinished;
    }

    boolean finish() {
        return isFinished;
    }
}
