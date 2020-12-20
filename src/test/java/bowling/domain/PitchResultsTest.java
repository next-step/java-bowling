package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchResultsTest {

    @DisplayName("PitchResults 현재까지의 결과 합계 테스트")
    @Test
    void sumUpTest(){
        PitchResults pitchResults = PitchResults.from(new LinkedList<>());
        pitchResults.addNewResult(10);
        pitchResults.addNewResult(10);

        // then
        assertThat(pitchResults.sumUpCurrentResult()).isEqualTo(10+10);
    }
}
