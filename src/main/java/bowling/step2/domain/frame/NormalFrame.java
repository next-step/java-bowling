package bowling.step2.domain.frame;

import java.util.Objects;

public class NormalFrame extends Frame{

    private static final int NORMAL_FRAME_MIN = 1;
    private static final int NORMAL_FRAME_MAX = 9;
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final int NEXT = 1;
    private static final String NORMAL_FRAME_EXCEPTION = "일반 프레임의 범위를 벗어났습니다.";

    public NormalFrame(int frameNo) {
        super(frameNo);
    }

    @Override
    void validateFrameNo(int frameNo) {
        if (frameNo < NORMAL_FRAME_MIN || frameNo > NORMAL_FRAME_MAX){
            throw new IllegalArgumentException(NORMAL_FRAME_EXCEPTION);
        }
    }

    @Override
    public Frame pitch(int pitch) {
        pitches.addPitch(pitch);
        pitches.validateNormalPitches();
        return this;
    }

    @Override
    public boolean pitchesOver() {
        return pitches.normalEnd();
    }

    @Override
    public Frame nextFrame() {
        if (nextFrameNo() == FINAL_FRAME_NUMBER){
            return new FinalFrame(FINAL_FRAME_NUMBER);
        }
        frameNo = nextFrameNo();
        return new NormalFrame(frameNo);
    }

    private int nextFrameNo() {
        return frameNo + NEXT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo &&
                Objects.equals(pitches, that.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, pitches);
    }

    @Override
    public String toString() {
        return pitches.toString();
    }
}
