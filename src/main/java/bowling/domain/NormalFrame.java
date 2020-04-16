package bowling.domain;

import java.util.Objects;

public class NormalFrame {
    private static final int MIN_FRAME_NUM = 1;
    private static final int MAX_FRAME_NUM = 10;
    private int falledPins = 0;

    private int frameNum;
    private Rolls rolls;

    public NormalFrame(int frameNum) {
        validateFrameNum(frameNum);
        this.frameNum = frameNum;
        this.rolls = new Rolls();
    }

    public NormalFrame(int frameNum, Rolls rolls) {
        this(frameNum);
        this.rolls = rolls;
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
        setPin(falledPins);
        return falledPins;
    }

    public NormalFrame createNextFrame() {
        return new NormalFrame(frameNum + 1);
    }

    public FinalFrame createFinalFrame(boolean bonusFlag) {
        return new FinalFrame(MAX_FRAME_NUM, bonusFlag);
    }

    private void setPin(int falledPins) {
        this.falledPins += falledPins;
        rolls.add(Pin.of(falledPins));
    }

    public int getFalledPins() {
        return falledPins;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public Rolls getRolls() {
        return rolls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof NormalFrame)) { return false; }
        final NormalFrame that = (NormalFrame) o;
        return getFrameNum() == that.getFrameNum() &&
               Objects.equals(rolls, that.rolls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrameNum(), rolls);
    }
}
