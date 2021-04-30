package bowling.domain;

public enum State {
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    OTHER;

    State() {}

    public static State of(int pinCount, boolean isFirst) {
        if (pinCount > 0 && pinCount % 10 == 0 && isFirst) {
            return STRIKE;
        }
        if (pinCount > 0 && pinCount % 10 == 0 && !isFirst) {
            return SPARE;
        }
        if (pinCount > 0 && pinCount < 10 && !isFirst) {
            return MISS;
        }
        if (pinCount == 0 && !isFirst) {
            return GUTTER;
        }
        return OTHER;
    }
}
