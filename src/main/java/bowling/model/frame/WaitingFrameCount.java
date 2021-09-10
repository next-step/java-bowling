package bowling.model.frame;

public class WaitingFrameCount {
    private static final int COUNT_OF_STRIKE = 2;
    private static final int COUNT_OF_SPARE = 1;
    private static final int NO_COUNT = 0;

    private int count;

    public WaitingFrameCount(int count) {
        validateRange(count);

        this.count = count;
    }

    public static WaitingFrameCount ofStrike() {
        return new WaitingFrameCount(COUNT_OF_STRIKE);
    }

    public static WaitingFrameCount ofSpare() {
        return new WaitingFrameCount(COUNT_OF_SPARE);
    }

    public static WaitingFrameCount noCount() {
        return new WaitingFrameCount(NO_COUNT);
    }

    private void validateRange(int count) {
        if (count < NO_COUNT || count > COUNT_OF_STRIKE) {
            throw new IllegalArgumentException(String.format("기다리고 있는 프레임 개수는 %d개 이상 %d개 이하이어야 합니다.",
                    NO_COUNT, COUNT_OF_STRIKE));
        }
    }
}
