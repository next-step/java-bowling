package bowling.step2.domain;

public class LastFrame implements Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private final int MAX = 10;

    private final int MAX_PITCH_SIZE = 3;

    private LastFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of();
    }

    public static LastFrame of(int frameNo) {
        return new LastFrame(frameNo);
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
        throw new RuntimeException("더이상 프레임을 생성할 수 없습니다.");
    }

    public int pitchCount() {
        return pitchGroup.size();
    }

    @Override
    public boolean finished() {
        return pitchGroup.size() == MAX_PITCH_SIZE;
    }
}
