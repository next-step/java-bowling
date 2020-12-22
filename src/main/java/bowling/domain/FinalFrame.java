package bowling.domain;

public class FinalFrame extends Frame {

    public static final int FIRST_PITCH_COUNT = 1;
    public static final int SECOND_PITCH_COUNT = 2;
    public static final int LAST_PITCH_COUNT = 3;
    public static final int MAX_FINAL_PIN_COUNT = 30;
    public static final String MAX_FINAL_PITCH_COUNT_OVER = String.format("마지막 프레임은 최대 %s회까지 투구가 가능합니다.", LAST_PITCH_COUNT);
    public static final String MAX_FINAL_PIN_COUNT_OVER = String.format("마지막 프레임은 최대 %s개를 초과하여 핀을 넘길 수 없습니다.", MAX_FINAL_PIN_COUNT);

    private FinalFrame(FrameNumber frameNumber) {
        super(frameNumber);
    }

    public static Frame from() {
        return new FinalFrame(FrameNumber.last());
    }

    @Override
    public Frame nextFrame() {
        return this;
    }

    @Override
    public void pitch(int pinCount) {
        validatePinCount(pinCount);
        pins.add(Pin.from(pinCount));
    }

    private void validatePinCount(int pinCount) {
        if ((getSumPinCount() + pinCount) > MAX_FINAL_PIN_COUNT) {
            throw new IllegalArgumentException(MAX_FINAL_PIN_COUNT_OVER);
        }

        if ((getPitchCount() + 1) > LAST_PITCH_COUNT) {
            throw new IllegalArgumentException(MAX_FINAL_PITCH_COUNT_OVER);
        }
    }

    @Override
    public Boolean isNextFrame() {
        return false;
    }

    @Override
    public Boolean isEnd() {
        return !isNextPinchAble();
    }

    private boolean isNextPinchAble() {
        if (isFirstPitchCount()) {
            return true;
        }

        if (isSecondPitchCount()) {
            return getSumPinCount() >= Pin.MAX_PIN_COUNT;
        }

        return !isLastPitchCount();
    }

    private boolean isFirstPitchCount() {
        return getPitchCount() == FIRST_PITCH_COUNT;
    }

    private boolean isSecondPitchCount() {
        return getPitchCount() == SECOND_PITCH_COUNT;
    }

    private boolean isLastPitchCount() {
        return getPitchCount() == LAST_PITCH_COUNT;
    }
}
