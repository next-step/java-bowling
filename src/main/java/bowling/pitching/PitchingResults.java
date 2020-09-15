package bowling.pitching;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PitchingResults {

    private List<PitchingResult> pitchResults;

    private PitchingResults(List<PitchingResult> pitchingResult) {
        this.pitchResults = pitchingResult;
    }

    public static PitchingResults from(List<PitchingResult> pitchingResult) {
        return new PitchingResults(pitchingResult);
    }

    public int size() {
        return pitchResults.size();
    }

    public List<PitchingResult> getPitchResults() {
        return Collections.unmodifiableList(pitchResults);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchingResults that = (PitchingResults) o;
        return Objects.equals(pitchResults, that.pitchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitchResults);
    }

    @Override
    public String toString() {
        return "PitchResults{" +
                "pitchResults=" + pitchResults +
                '}';
    }
}
