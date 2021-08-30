package bowling.step2.domain;

public class Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private final int MAX = 10;

    private Frame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of();
    }

    public static Frame of(int frameNo) {
        return new Frame(frameNo);
    }

    public void pitch(int count) {
        validatePitchCount(pitchGroup.lastPitchCount(), count);
        pitchGroup.pitch(count);
    }

    private void validatePitchCount(int lastPitchCount, int count) {
        if (sumOfPitchesOverTheMax(lastPitchCount, count)) {
            throw new RuntimeException("쓰러뜨릴 수 있는 핀의 갯수를 넘어섰습니다.");
        }
    }

    private boolean sumOfPitchesOverTheMax(int lastPitchCount, int count) {
        return lastPitchCount + count > MAX;
    }
}
