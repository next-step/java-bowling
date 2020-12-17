package bowling.domain;

import java.util.Objects;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class Frame {

    public static final int INIT = 0;
    public static final int NEXT_NO = 1;
    public static final int FINAL_FRAME_NO = 10;

    private final int frameNo;
    private Score score;

    public Frame(int frameNo) {
        System.out.println(frameNo);
        this.frameNo = frameNo;
    }

    public Frame bowl(int downPins) {
        if (isFinalFrame()) {
            return new FinalFrame(downPins);
        }
        if (isStrike(downPins)) {
            this.score = new Score(downPins);
            return new Frame(frameNo + NEXT_NO);
        }
        if (isSecond()) {
            this.score.setSecond(downPins);
            return new Frame(frameNo + NEXT_NO);
        }

        this.score = new Score(downPins);
        return this;
    }

    private boolean isFinalFrame() {
        return this.frameNo == FINAL_FRAME_NO;
    }

    public boolean isStrike(int downPins) {
        return downPins == Symbol.STRIKE.getScore();
    }

    private boolean isSecond() {
        return score != null;
    }

    public int getFrameNo() {
//        return frameNo < 10 ? "0" + frameNo : String.valueOf(frameNo);
        return frameNo;
    }

    public Score getScore() {
        return score;
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
