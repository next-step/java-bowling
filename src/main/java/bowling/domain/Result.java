package bowling.domain;

public enum Result {
    STRIKE,
    SPARE,
    GUTTER,
    MISS;

    public static Result from(Frame frame, int count) {
        return from(frame, PinCount.of(count));
    }

    public static Result from(Frame frame, PinCount pinCount) {
        int round = frame.size();

        if (round == 0 || round == 2) {
            return Result.of(pinCount);
        }

        if (round == 1) {
            return Result.second(frame.getLastCount(), pinCount);
        }

        throw new IllegalArgumentException("투구는 3회 이하여야 합니다.");
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

        if (currentCount.sum(beforeCount).isTen()) {
            return SPARE;
        }

        return MISS;
    }
}
