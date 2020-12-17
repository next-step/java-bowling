package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PitchingResults {
    private static final int MAXIMUM_SIZE = 3;
    private final List<Pins> pitchingResults;

    public PitchingResults(final List<Pins> pitchingResults) {
        this.pitchingResults = pitchingResults;
    }

    public static PitchingResults of() {
        return new PitchingResults(new ArrayList<>(MAXIMUM_SIZE));
    }

    public boolean add(final Pins pitchingResult) {
        if (pitchingResults.size() > MAXIMUM_SIZE) {
            throw new IllegalStateException();
        }
        return pitchingResults.add(pitchingResult);
    }

    public Pins get(final int index) {
        if (lastIndex() < index) {
            return null;
        }
        return pitchingResults.get(index);
    }

    private int lastIndex() {
        return pitchingResults.size() - 1;
    }

    public List<Integer> getAllFallenPin() {
        return pitchingResults.stream()
                .map(Pins::getFallenPin)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public boolean isEmpty() {
        return pitchingResults.size() == 0;
    }
}
