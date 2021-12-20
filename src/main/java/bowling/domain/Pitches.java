package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pitches {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    private final List<Pitch> pitches = new ArrayList<>();

    private Pitches() {
    }

    public static Pitches init() {
        return new Pitches();
    }

    public void add(Pitch pitch) {
        pitches.add(pitch);
    }

    public boolean isStrike() {
        int currentPitchIndex = currentPitchIndex();
        Pitch currentPitch = pitch(currentPitchIndex);
        if (retryable()) {
            int previousPitchIndex = currentPitchIndex - ONE;
            return currentPitch.isStrike(pitch(previousPitchIndex));
        }
        return currentPitch.isStrike();
    }

    public boolean isSpare() {
        int currentPitchIndex = currentPitchIndex();
        if (retryable()) {
            Pitch currentPitch = pitch(currentPitchIndex);
            int previousPitchIndex = currentPitchIndex - ONE;
            return currentPitch.isSpare(pitch(previousPitchIndex));
        }
        return false;
    }

    public List<String> pitchResults() {
        List<String> result = new ArrayList<>();
        for (int pitchNo = ZERO; pitchNo < pitches.size(); pitchNo++) {
            result.add(pitchSymbol(pitchNo, pitch(pitchNo)));
        }
        return result;
    }

    private String pitchSymbol(int pitchNo, Pitch pitch) {
        if (pitchNo >= ONE) {
            if (pitch.isStrike(pitch(pitchNo - ONE))) {
                return STRIKE;
            }
            if (pitch.isSpare(pitch(pitchNo - ONE))) {
                return SPARE;
            }
        }
        if (pitch.isStrike()) {
            return STRIKE;
        }
        if (pitch.isGutter()) {
            return GUTTER;
        }
        return Integer.toString(pitch.fallDownPinsSize());
    }

    public int fallDownPinsSize() {
        return currentPitch().fallDownPinsSize();
    }

    public Pitch currentPitch() {
        return pitch(currentPitchIndex());
    }

    public int currentPitchIndex() {
        return currentSize() - ONE;
    }

    public boolean isEmpty() {
        return pitches.isEmpty();
    }

    public boolean isThirdPitch() {
        return size() == THREE;
    }

    public boolean retryable() {
        return size() >= TWO;
    }

    private int currentSize() {
        int size = size();
        if (size == 0) {
            throw new IllegalArgumentException("생성된 투구가 존재하지 않습니다.");
        }
        return size;
    }

    private int size() {
        return pitches.size();
    }

    private Pitch pitch(int index) {
        return pitches.get(index);
    }

    public List<Pitch> pitches() {
        return pitches;
    }
}
