package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class FrameGenerator {
    private static final int TRY_ONE = 1;
    private static final int TRY_TWO = 2;


    private FrameGenerator() {}

    public static Frame generate(FrameType type) {
        if (FrameType.NORMAL == type) {
            return new NormalFrame();
        }

        if (FrameType.BONUS_TRY_ONE == type) {
            return new BonusFrame(TRY_ONE);
        }

        if (FrameType.BONUS_TRY_TWO == type) {
            return new BonusFrame(TRY_TWO);
        }

        throw new IllegalArgumentException("형식에 맞는 Frame 이 없습니다");
    }
}
