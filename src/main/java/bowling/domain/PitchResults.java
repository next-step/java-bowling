package bowling.domain;

import java.util.List;
import java.util.Objects;

public class PitchResults {

    private final int BOWLING_PIN_COUNT = 10;

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
        int sumUpCurrentResult = pitchResults.stream()
                .mapToInt(pitchResult -> pitchResult.getPinCount())
                .sum();
        return sumUpCurrentResult;
    }

    public void addNewResult(int pitchResult) {
        pitchResults.add(PitchResult.from(pitchResult));
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

    public boolean isSpare() {
        return (pitchResults.size() >= 2) &&
                (pitchResults.stream()
                        .limit(2)
                        .mapToInt(PitchResult::getPinCount)
                        .sum() == BOWLING_PIN_COUNT);

    }

    public boolean isStrike() {
        return (!pitchResults.isEmpty()) && (pitchResults.get(0).isStrike());
    }

    public String toStringFirstResult() {
        return pitchResults.get(0).toString();
    }
}
