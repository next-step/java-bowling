package bowling.domain;

public class FrameFactory {
    public static final int MAX_INDEX = 9;

    public static Frame create(int index) {
        if (index == MAX_INDEX) {
            return new BonusFrame();
        }
        return new NormalFrame();
    }
}
