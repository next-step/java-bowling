package bowling.domain;

public class Ready implements FrameState {

    private static final int STRIKE_FALLEN_PIN_COUNT = 10;

    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        if (fallenPinCount.count() < STRIKE_FALLEN_PIN_COUNT) {
            return new Proceeding();
        }
        return new Strike();
    }
}
