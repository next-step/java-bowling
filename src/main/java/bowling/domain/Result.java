package bowling.domain;

public enum Result {
    STRIKE,
    SPARE,
    GUTTER,
    MISS;

    public static Result from(Frame frame, int count) {
        int round = frame.size();

        if (round == 0 || round == 2) {
            return Result.of(count);
        }

        if (round == 1) {
            return Result.second(frame.getLastCount(), count);
        }

        throw new IllegalArgumentException("투구는 3회 이하여야 합니다.");
    }

    private static Result of(int count) {
        if (count == 10) {
            return STRIKE;
        }

        if (count == 0) {
            return GUTTER;
        }

        return MISS;
    }

    private static Result second(int beforeCount, int currentCount) {
        if (currentCount == 0) {
            return GUTTER;
        }

        if (beforeCount + currentCount == 10) {
            return SPARE;
        }

        return MISS;
    }
}
