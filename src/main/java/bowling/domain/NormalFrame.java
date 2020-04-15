package bowling.domain;

import java.util.Objects;

public class NormalFrame {
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
        if (frameNum < Rule.MIN_FRAME_NUM.getValue()) {
            throw new IllegalArgumentException("프레임 넘버는 1미만 일 수 없습니다.");
        }

        if (frameNum > Rule.MAX_FRAME_NUM.getValue()) {
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

    public NormalFrame createFinalFrame(boolean bonusFlag) {
        return new FinalFrame(Rule.MAX_FRAME_NUM.getValue(), bonusFlag);
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
