package bowling.domain;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class PitchResults {

    private List<PitchResult> pitchResults;

    private PitchResults(List<PitchResult> pitchResults){
        this.pitchResults = pitchResults;
    }

    public static PitchResults from(List<PitchResult> pitchResults){
        return new PitchResults(pitchResults);
    }

    public List<PitchResult> getPitchResults() {
        return pitchResults;
    }

    public int sumUpCurrentResult() {
        return pitchResults.stream()
                .mapToInt(pitchResult -> pitchResult.getPinCount())
                .sum();
    }

    public void addNewResult(int pitchResult) {
        pitchResults.add(PitchResult.from(pitchResult));
    }

    public int size() {
        return pitchResults.size();
    }

    public int findResult(int index) {
        return pitchResults.get(index).getPinCount();
    }

    public int findLast() {
        return pitchResults.stream()
                .reduce((first, second) -> second)
                .map(pitchResult -> pitchResult.getPinCount())
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchResults that = (PitchResults) o;
        return Objects.equals(pitchResults, that.pitchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchResults);
    }
}
