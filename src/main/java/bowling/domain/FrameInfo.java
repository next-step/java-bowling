package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.List;

public class FrameInfo {
    private static final int DEFAULT_NO = 0;
    private static final int INCREASE_NO_COUNT = 1;
    private static final int END_FRAME_PREVIOUS_NO = 8;
    private static final int END_FRAME_NO = 9;
    private static final int PINS_MAX_COUNT = 10;

    private final int no;
    private final Pitches pitches;

    private FrameInfo(int no) {
        this(no, Pitches.init());
    }

    private FrameInfo(int no, Pitches pitches) {
        this.no = no;
        this.pitches = pitches;
    }

    public static FrameInfo init() {
        return new FrameInfo(DEFAULT_NO);
    }

    public static FrameInfo create(int no) {
        return new FrameInfo(no);
    }

    public FrameInfo next() {
        return new FrameInfo(this.no + INCREASE_NO_COUNT);
    }

    public int no() {
        return no;
    }

    public int fallDownPinsCount() {
        return pitches.fallDownPinsSize();
    }

    public boolean last() {
        return no == END_FRAME_NO;
    }

    public boolean nextLast() {
        return no == END_FRAME_PREVIOUS_NO;
    }

    public Pitch createPitch(PitchNumberStrategy numberStrategy) {
        if (isPitchesEmpty()) {
            return Pitch.first(numberStrategy.generate(PINS_MAX_COUNT));
        }
        return currentPitch().next(numberStrategy);
    }

    private Pitch currentPitch() {
        return pitches.currentPitch();
    }

    private boolean isPitchesEmpty() {
        return pitches.isEmpty();
    }

    public boolean isSecondPitch() {
        return pitches.isSecondPitch();
    }

    public boolean isThirdPitch() {
        return pitches.isThirdPitch();
    }

    public void addPitch(Pitch pitch) {
        pitches.add(pitch);
    }

    public boolean isStrike() {
        return pitches.isStrike();
    }

    public boolean isSpare() {
        return pitches.isSpare();
    }

    public List<String> pitchResults() {
        return pitches.pitchResults();
    }

    @Override
    public String toString() {
        return "FrameInfo{" +
                "no=" + no +
                ", pitches=" + pitches +
                '}';
    }
}
