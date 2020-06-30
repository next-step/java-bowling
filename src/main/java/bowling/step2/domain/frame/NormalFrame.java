package bowling.step2.domain.frame;

import java.util.Objects;

public class NormalFrame extends Frame{

    public NormalFrame(int frameNo) {
        super(frameNo);
    }

    @Override
    void validateFrameNo(int frameNo) {
        if (frameNo < 1 || frameNo > 9){
            throw new IllegalArgumentException("일반 프레임의 범위를 벗어났습니다.");
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
        if (nextFrameNo() == 10){
            return new FinalFrame(10);
        }
        frameNo = nextFrameNo();
        return new NormalFrame(frameNo);
    }

    private int nextFrameNo() {
        return frameNo + 1;
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
