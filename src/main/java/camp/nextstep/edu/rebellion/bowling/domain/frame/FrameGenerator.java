package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class FrameGenerator {
    private FrameGenerator() {}

    public static Frame generate(FrameType type) {
        if (FrameType.NORMAL == type) {
            return new NormalFrame();
        }

        if (FrameType.BONUS == type) {
            return new BonusFrame();
        }

        throw new IllegalArgumentException("형식에 맞는 Frame 이 없습니다");
    }
}
