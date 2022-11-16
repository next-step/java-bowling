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
            return normalSecond(frame.beforeCount(), pinCount);
            //return Result.second(frame, pinCount);
        }

        throw new IllegalArgumentException("투구는 3회 이상일수 없습니다.");
    }

    @Deprecated
    private static Result second(Frame frame, PinCount pinCount) {
        if (frame.isFinal()) {
            return finalSecond(frame.beforeCount(), pinCount);
        }
        return normalSecond(frame.beforeCount(), pinCount);
    }

    @Deprecated
    private static Result finalSecond(PinCount beforeCount, PinCount currentCount) {

        if (currentCount.isTen()) {
            return STRIKE;
        }

        int sum = currentCount.sum(beforeCount);
        if (sum == MAX_COUNT) {
            return SPARE;
        }

        if (currentCount.isZero()) {
            return GUTTER;
        }

        return MISS;
    }

    private static Result normalSecond(PinCount beforeCount, PinCount currentCount) {
        if (beforeCount.isTen() && currentCount.isTen()) {
            return STRIKE;
        }

        int sum = currentCount.sum(beforeCount);
        if (sum > MAX_COUNT) {
            throw new IllegalArgumentException(String.format("이전 투구(%s) + 현재투구(%s)는 %d을 넘을수 없습니다.", beforeCount, currentCount, MAX_COUNT));
        }

        if (sum == MAX_COUNT) {
            return SPARE;
        }

        if (currentCount.isZero()) {
            return GUTTER;
        }


        return MISS;
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


    public boolean isStrike() {
        return Result.STRIKE == this;
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
