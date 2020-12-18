package bowling.domain;

import java.util.Objects;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class Frame {
    public static final Pins ALL_PINS = new Pins(10);
    public static final int INIT = 1;
    public static final int NEXT_NO = 1;
    public static final String FORMATTER_ZERO = "0";

    private final int frameNo;
    protected Score score;

    public Frame(int frameNo) {
        this.frameNo = frameNo;
    }

    public Frame(int frameNo, Score score) {
        this.frameNo = frameNo;
        this.score = score;
    }

    public Frame bowl(Pins downPins) {
        if (isStrike(downPins)) {
            this.score = new Score(downPins);
            return isNextFinalFrame() ? new FinalFrame(downPins) : new Frame(frameNo + NEXT_NO);
        }
        if (isSecond()) {
            this.score.setSecond(downPins);
            return isNextFinalFrame() ? new FinalFrame(downPins) : new Frame(frameNo + NEXT_NO);
        }

        this.score = new Score(downPins);
        return this;
    }

    private boolean isNextFinalFrame() {
        return this.frameNo + NEXT_NO == Frames.ALL_FRAMES;
    }

    public boolean isStrike(Pins downPins) {
        return downPins.get() == Symbol.STRIKE.getScore();
    }

    private boolean isSecond() {
        return score != null;
    }

    public int getFrameNo() {
        return frameNo;
    }

    public String getFrameNoString() {
        return frameNo < Frames.ALL_FRAMES ? FORMATTER_ZERO + frameNo : String.valueOf(frameNo);
    }

    public Score getScore() {
        return score;
    }

    public Pins remainPins() {
        return new Pins(Frame.ALL_PINS.get() - this.score.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNo == frame.frameNo && score.equals(frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, score);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "frameNo=" + frameNo +
                ", score=" + score +
                '}';
    }
}
