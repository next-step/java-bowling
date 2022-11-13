package bowling.domain;

public class FrameFactory {
    public static Frame create(int index) {
        if (index == 9) {
            return new BonusFrame();
        }
        return new NormalFrame();
    }
}
