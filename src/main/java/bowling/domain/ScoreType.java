package bowling.domain;

public enum ScoreType {
    STRIKE(true, true),
    SPARE(true, true),
    MISS_FIRST(false, false),
    MISS_SECOND(true, false),
    GUTTER_FIRST(false, false),
    GUTTER_SECOND(true, false);

    private final boolean isFinished;
    private final boolean isCleared;

    ScoreType(boolean isFinished, boolean isCleared) {
        this.isFinished = isFinished;
        this.isCleared = isCleared;
    }

    boolean finish() {
        return isFinished;
    }

    boolean isCleared(){
        return isCleared;
    }
}
