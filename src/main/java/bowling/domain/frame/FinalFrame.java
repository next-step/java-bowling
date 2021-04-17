package bowling.domain;

import static bowling.domain.NormalFrame.MAX_PITCH;

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
        if ((pinCountSum() + pinCount) > Pin.MAX_PIN_COUNT) {
            throw new IllegalArgumentException("핀의 개수가 10개를 넘을 수 없습니다.");
        }

        if ((getPinsSize() + 1) > MAX_PITCH) {
            throw new IllegalArgumentException("최대 2회 투구 가능합니다.");
        }
    }

    @Override
    public Boolean isNextFrame() {
        return false;
    }

    @Override
    boolean isLast() {
        if (getPinsSize() == FIRST_PITCH) {
            return false;
        }

        if (getPinsSize() == SECOND_PITCH) {
            return pinCountSum() < Pin.MAX_PIN_COUNT;
        }

        return getPinsSize() != BONUS_PITCH;
    }
}
