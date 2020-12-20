package bowling.domain;

public enum Condition {
    READY,
    PLAYING,
    FINISH_NOT_PERFECT,
    FINISH_PERFECT;


    boolean isFinished() {
        return this == FINISH_NOT_PERFECT || this == FINISH_PERFECT;
    }

}
