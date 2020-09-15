package bowling.pitching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PitchingResultsTest {

    private PitchingResult pitchResult;
    private PitchingResults pitchResults;

    @Test
    @DisplayName("투구 2번 확인")
    void pitching() {
        List<PitchingResult> pitchingResults = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            pitchResult = PitchingResult.from(Pitching.pitch("5", i));
            pitchingResults.add(pitchResult);
        }
        pitchResults = PitchingResults.from(pitchingResults);
        assertThat(pitchResults.size()).isEqualTo(2);
    }
}
