package bowling.step2.domain;

import bowling.step2.domain.visitor.FrameVisitor;

import java.util.List;

public class LastFrame implements Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private final int MAX_PITCH_TOTAL_COUNT = 10;

    private static final int MAX_PITCH_SIZE = 3;

    private static final int NORMAL_PITCH_SIZE = 2;

    private LastFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of();
    }

    public static LastFrame of(int frameNo) {
        return new LastFrame(frameNo);
    }

    @Override
    public void pitch(int count) {
        validateAdditionalPitch();
        validatePitchCount(pitchGroup.lastPitchCount(), count);
        pitchGroup.pitch(count);
    }

    private void validateAdditionalPitch() {
        if (pitchGroupSizeIsTwo() && totalCountIsLessThanMax()) {
            throw new RuntimeException("더이상 공을 던질 수 없습니다.");
        }
    }

    private boolean totalCountIsLessThanMax() {
        return pitchGroup.total() < MAX_PITCH_TOTAL_COUNT;
    }

    private boolean pitchGroupSizeIsTwo() {
        return pitchGroup.size() == NORMAL_PITCH_SIZE;
    }


    private void validatePitchCount(int lastPitchCount, int count) {
        if (sumWithLastPitchOverTheMax(lastPitchCount, count)) {
            throw new RuntimeException("쓰러뜨릴 수 있는 핀의 갯수를 넘어섰습니다.");
        }
    }

    private boolean sumWithLastPitchOverTheMax(int lastPitchCount, int count) {
        return lastPitchCount + count > MAX_PITCH_TOTAL_COUNT;
    }

    @Override
    public Frame nextFrame(FrameVisitor frameVisitor) {
        return frameVisitor.nextFrame(this);
    }

    @Override
    public boolean finished() {
        return (pitchGroupSizeIsTwo() && totalCountIsLessThanMax()) || pitchGroup.size() == MAX_PITCH_SIZE;
    }

    @Override
    public List<Integer> current() {
        return pitchGroup.pitches();
    }
}
