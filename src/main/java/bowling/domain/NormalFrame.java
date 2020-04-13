package bowling.domain;

public class NormalFrame {
    private static final int MIN_FRAME_NUM = 1;
    private static final int MAX_FRAME_NUM = 10;

    private int frameNum;
    private int bowlCount = 2;
    private Pin pin;

    public NormalFrame(int frameNum) {
        validateFrameNum(frameNum);
        this.frameNum = frameNum;
    }

    public void validateFrameNum(int frameNum) {
        if (frameNum < MIN_FRAME_NUM) {
            throw new IllegalArgumentException("프레임 넘버는 1미만 일 수 없습니다.");
        }

        if (frameNum > MAX_FRAME_NUM) {
            throw new IllegalArgumentException("프레임 넘버는 10초과 일 수 없습니다.");
        }
    }

    public int bowl(int falledPins) {
        if (bowlCount == 2) {
            setPin(falledPins);
            return falledPins;
        }

        int existFalledPins = pin.getFalledPins();
        falledPins += existFalledPins;
        setPin(falledPins);

        return falledPins;
    }

    private void setPin(int falledPins) {
        --bowlCount;
        pin = Pin.of(falledPins);
    }

    public Pin getPin() {
        return pin;
    }
}
