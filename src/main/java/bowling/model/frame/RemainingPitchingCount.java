package bowling.model.frame;

public class RemainingPitchingCount {
    private static final int COUNT_OF_STRIKE = 2;
    private static final int COUNT_OF_SPARE = 1;
    private static final int ONE_COUNT = 1;
    private static final int NO_COUNT = 0;

    private int count;

    public RemainingPitchingCount(int count) {
        validateRange(count);

        this.count = count;
    }

    public static RemainingPitchingCount strike() {
        return new RemainingPitchingCount(COUNT_OF_STRIKE);
    }

    public static RemainingPitchingCount spare() {
        return new RemainingPitchingCount(COUNT_OF_SPARE);
    }

    public static RemainingPitchingCount secondAndNotSpare() {
        return new RemainingPitchingCount(NO_COUNT);
    }

    public static RemainingPitchingCount firstAndNotStrike() {
        return new RemainingPitchingCount(ONE_COUNT);
    }

    private void validateRange(int count) {
        if (count < NO_COUNT || count > COUNT_OF_STRIKE) {
            throw new IllegalArgumentException(String.format("남은 투구 개수는 %d개 이상 %d개 이하이어야 합니다.",
                    NO_COUNT, COUNT_OF_STRIKE));
        }
    }

    public int count() {
        return count;
    }

    public boolean isNoCount() {
        return count == NO_COUNT;
    }

    public void decreaseOne() {
        count--;
    }
}
