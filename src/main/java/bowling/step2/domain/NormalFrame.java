package bowling.step2.domain;

import java.util.List;

public class NormalFrame implements Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private static final int MAX = 10;

    private static final int MAX_PITCH_SIZE = 2;

    private static final int MAX_NORMAL_FRAME_NUM = 9;

    private NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of();
    }

    public static NormalFrame of(int frameNo) {
        return new NormalFrame(frameNo);
    }

    @Override
    public void pitch(int count) {
        validatePitchCount(pitchGroup.lastPitchCount(), count);
        pitchGroup.pitch(count);
    }

    private void validatePitchCount(int lastPitchCount, int count) {
        if (sumWithLastPitchOverTheMax(lastPitchCount, count)) {
            throw new RuntimeException("쓰러뜨릴 수 있는 핀의 갯수를 넘어섰습니다.");
        }
    }

    private boolean sumWithLastPitchOverTheMax(int lastPitchCount, int count) {
        return lastPitchCount + count > MAX;
    }

    @Override
    public Frame nextFrame() {
        if (isLastOfNormalFrame()) {
            return LastFrame.of(this.frameNo + 1);
        }

        return NormalFrame.of(this.frameNo + 1);
    }

    private boolean isLastOfNormalFrame() {
        return this.frameNo == MAX_NORMAL_FRAME_NUM;
    }

    @Override
    public boolean finished() {
        return pitchGroup.size() == MAX_PITCH_SIZE || pitchGroup.total() == MAX;
    }

    @Override
    public List<Integer> current() {
        return pitchGroup.pitches();
    }
}
