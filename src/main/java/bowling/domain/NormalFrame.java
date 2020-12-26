package bowling.domain;

public class NormalFrame extends Frame {

    public static final int MAX_PITCH_COUNT = 2;
    public static final String MAX_PITCH_COUNT_OVER = String.format("프레임당 최대 %s회까지 투구가 가능합니다.", MAX_PITCH_COUNT);
    public static final String MAX_PIN_COUNT_OVER = String.format("프레임당 %s개를 초과하여 핀을 넘길 수 없습니다.",Pin.MAX_PIN_COUNT);

    private NormalFrame(FrameNumber frameNumber) {
        super(frameNumber);
    }

    public static Frame from(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame nextFrame() {
        FrameNumber nextNumber = frameNumber.add();
        return from(nextNumber);
    }

    @Override
    public void pitch(int pinCount) {
        validatePinCount(pinCount);
        pins.add(Pin.from(pinCount));
    }

    private void validatePinCount(int pinCount) {
        if ((getSumPinCount() + pinCount) > Pin.MAX_PIN_COUNT) {
            throw new IllegalArgumentException(MAX_PIN_COUNT_OVER);
        }

        if ((getPitchCount() + 1) > MAX_PITCH_COUNT) {
            throw new IllegalArgumentException(MAX_PITCH_COUNT_OVER);
        }
    }

    @Override
    public Boolean isNextFrame() {
        if (getPitchCount() == MAX_PITCH_COUNT) {
            return true;
        }

        return checkMaxPinCount();
    }

    private Boolean checkMaxPinCount() {
        return getSumPinCount() == Pin.MAX_PIN_COUNT;
    }

    @Override
    public Boolean isEnd() {
        return false;
    }
}
