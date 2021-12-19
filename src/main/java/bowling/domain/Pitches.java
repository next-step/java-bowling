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

    public List<String> pitchResults() {
        List<String> result = new ArrayList<>();
        for (int pitchNo = ZERO; pitchNo < pitches.size(); pitchNo++) {
            result.add(pitchSymbol(pitchNo, getPitch(pitchNo)));
        }
        return result;
    }

    private String pitchSymbol(int pitchNo, Pitch pitch) {
        if (pitchNo >= ONE) {
            if (pitch.isSecondStrike(getPitch(pitchNo - ONE))) {
                return STRIKE;
            }
            if (pitch.isSpare(getPitch(pitchNo - ONE))) {
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
        return getPitch(currentPitchIndex());
    }

    public int currentPitchIndex() {
        return currentSize() - ONE;
    }

    public boolean isEmpty() {
        return pitches.isEmpty();
    }

    public boolean isSecondPitch() {
        return size() == TWO;
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

    private Pitch getPitch(int index) {
        return pitches.get(index);
    }
}
