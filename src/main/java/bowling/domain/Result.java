package bowling.domain;

public enum Result {
    STRIKE,
    SPARE,
    GUTTER,
    MISS,
    NONE;

    public static final int FIRST_ROUND = 0;
    public static final int SECOND_ROUND = 1;
    public static final int FINAL_ROUND = 2;
    public static final int MAX_COUNT = 10;

    public static Result from(Frame frame, int count) {
        return from(frame, PinCount.of(count));
    }

    public static Result from(Frame frame, PinCount pinCount) {
        int round = frame.getRound();

        if (round == FIRST_ROUND || round == FINAL_ROUND) {
            return Result.of(pinCount);
        }

        if (round == SECOND_ROUND) {
            return Result.second(frame.beforeCount(), pinCount);
        }

        throw new IllegalArgumentException("투구는 3회 이하여야 합니다. " + round);
    }

    private static Result of(PinCount pinCount) {
        if (pinCount.isTen()) {
            return STRIKE;
        }

        if (pinCount.isZero()) {
            return GUTTER;
        }

        return MISS;
    }

    public static Result of(int count) {
        return of(PinCount.of(count));
    }

    private static Result second(PinCount beforeCount, PinCount currentCount) {
        if (currentCount.isZero()) {
            return GUTTER;
        }

        if (currentCount.sum(beforeCount) == MAX_COUNT) {
            return SPARE;
        }

        return MISS;
    }

    public boolean isStrike() {
        return Result.STRIKE == this;
    }

    public boolean isSpare() {
        return Result.SPARE == this;
    }

    public boolean isMiss() {
        return Result.MISS == this;
    }

    public boolean isGutter() {
        return Result.GUTTER == this;
    }

    public boolean isNone() {
        return Result.NONE == this;
    }

}
