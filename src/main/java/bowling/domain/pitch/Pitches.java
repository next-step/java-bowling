package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pitches {
    private static final int ONE = 1;

    private final List<Pitch> pitches = new ArrayList<>();

    private Pitches() {
    }

    public static Pitches init() {
        return new Pitches();
    }

    public void add(Pitch pitch) {
        pitches.add(pitch);
    }

    public List<Integer> fallDownPinsAll() {
        return pitches.stream()
                .map(Pitch::fallDownPinsSize)
                .collect(Collectors.toList());
    }

    public int currentFallDownPinsSize() {
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
}
