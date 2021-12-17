package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameInfo {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int END_FRAME_PREVIOUS_NO = 8;
    private static final int END_FRAME_NO = 9;

    private final int no;
    private final List<Pitch> pitches = new ArrayList<>();

    private FrameInfo(int no) {
        this.no = no;
    }

    public static FrameInfo init() {
        return new FrameInfo(0);
    }

    public static FrameInfo create(int no) {
        return new FrameInfo(no);
    }

    public FrameInfo next() {
        return new FrameInfo(this.no + ONE);
    }

    public int no() {
        return no;
    }

    public List<Pitch> pitches() {
        return Collections.unmodifiableList(pitches);
    }

    public boolean retryable() {
        return pitches.size() >= TWO;
    }

    public boolean isSecondPitch() {
        return pitches.size() == TWO;
    }

    public boolean isThirdPitch() {
        return pitches.size() == THREE;
    }

    public void addPitch(Pitch pitch) {
        pitches.add(pitch);
    }

    public boolean last() {
        return no == END_FRAME_NO;
    }

    public boolean nextLast() {
        return no == END_FRAME_PREVIOUS_NO;
    }

    public boolean isStrike() {
        int currentPitchIndex = currentPitchIndex();
        Pitch currentPitch = getPitch(currentPitchIndex);
        if (retryable()) {
            int previousPitchIndex = currentPitchIndex - ONE;
            return currentPitch.isSecondStrike(getPitch(previousPitchIndex));
        }
        return currentPitch.isStrike();
    }

    public boolean isSpare() {
        int currentPitchIndex = currentPitchIndex();
        if (retryable()) {
            Pitch currentPitch = getPitch(currentPitchIndex);
            int previousPitchIndex = currentPitchIndex - ONE;
            return currentPitch.isSpare(getPitch(previousPitchIndex));
        }
        return false;
    }

    private Pitch getPitch(int no) {
        return pitches.get(no);
    }

    private int currentPitchIndex() {
        return pitches.size() - ONE;
    }

    @Override
    public String toString() {
        return "FrameInfo{" +
                "no=" + no +
                ", pitches=" + pitches +
                '}';
    }
}
