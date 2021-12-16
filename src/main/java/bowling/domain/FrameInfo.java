package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameInfo {
    private static final int INDEX_INCREASE_COUNT = 1;
    private static final int END_FRAME_PREVIOUS_NO = 8;
    private static final int END_FRAME_NO = 9;
    private static final int TWO = 2;

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
        return new FrameInfo(this.no + INDEX_INCREASE_COUNT);
    }

    public int no() {
        return no;
    }

    public List<Pitch> pitches() {
        return pitches;
    }

    public int pitchesSize() {
        return pitches.size();
    }

    public boolean isSecondPitch() {
        return pitches.size() == TWO;
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

    @Override
    public String toString() {
        return "FrameInfo{" +
                "no=" + no +
                ", pitches=" + pitches +
                '}';
    }
}
