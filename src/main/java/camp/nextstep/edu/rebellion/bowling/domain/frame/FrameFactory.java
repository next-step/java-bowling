package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class FrameFactory {
    private static final int DEFAULT_TRY = 2;

    private FrameFactory() {}

    public static Frame get(FrameType type) {
        return get(type, DEFAULT_TRY);
    }

    public static Frame get(FrameType type, int initAttempt) {
        if (FrameType.NORMAL == type) {
            return new NormalFrame(initAttempt);
        }

        if (FrameType.BONUS == type) {
            return new BonusFrame(initAttempt);
        }

        throw new IllegalArgumentException("형식에 맞는 Frame 이 없습니다");
    }
}
