package bowling.domain;

public class NormalFrame extends Frame {

    public static final int MAX_PITCH = 2;

    public NormalFrame(FrameRound frameRound) {
        super(frameRound);
    }

    public static Frame get(FrameRound frameRound) {
        return new NormalFrame(frameRound);
    }

    @Override
    Frame next() {
        FrameRound nextFrameRound = frameRound.next();
        return new NormalFrame(nextFrameRound);
    }

    @Override
    public void pitch(int pinCount) {
        validatePinCount(pinCount);
        pins.add(Pin.from(pinCount));
    }

    private void validatePinCount(int pinCount) {
        if ((pinCountSum() + pinCount) > Pin.MAX_PIN_COUNT) {
            throw new IllegalArgumentException("핀의 개수가 30개를 넘을 수 없습니다.");
        }

        if ((getPinsSize() + 1) > BONUS_PITCH) {
            throw new IllegalArgumentException("최대 3회 투구 가능합니다.");
        }
    }

    @Override
    public Boolean isNextFrame() {
        if (getPinsSize() == MAX_PITCH) {
            return true;
        }

        return pinCountSum() == Pin.MAX_PIN_COUNT;
    }

    @Override
    boolean isLast() {
        return false;
    }


}
