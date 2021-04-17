package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.MAX_PITCH;

public class FinalFrame extends Frame {

    public static final int MAX_FINAL_PIN_COUNT = 30;

    public FinalFrame(FrameRound frameRound) {
        super(frameRound);
    }

    public static FinalFrame get() {
        return new FinalFrame(FrameRound.last());
    }

    @Override
    Frame next() {
        return this;
    }

    @Override
    public void pitch(int pinCount) {
        validatePinCount(pinCount);
        pins.add(Pin.from(pinCount));
    }

    private void validatePinCount(int pinCount) {
        if ((pinCountSum() + pinCount) > MAX_FINAL_PIN_COUNT) {
            throw new IllegalArgumentException("핀의 개수가 30개를 넘을 수 없습니다.");
        }

        if ((getPinsSize() + 1) > BONUS_PITCH) {
            throw new IllegalArgumentException("최대 3회 투구 가능합니다.");
        }
    }

    @Override
    public Boolean isNextFrame() {
        return false;
    }

    @Override
    boolean isLast() {
        return !isNextPitch();
    }

    private boolean isNextPitch() {
        if (getPinsSize() == FIRST_PITCH) {
            return true;
        }

        if (getPinsSize() == SECOND_PITCH) {
            return pinCountSum() >= Pin.MAX_PIN_COUNT;
        }

        return getPinsSize() != BONUS_PITCH;
    }
}
