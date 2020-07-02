package bowling;

import bowling.domain.BowlingGameResult;
import bowling.domain.BowlingGameResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bowling.domain.FakeDataForTest.NORMAL_STRIKE_FRAME_RESULT;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameResultsTests {
    @DisplayName("BowlingGameResult 리스트를 주입받아서 객체를 생성 할 수 있다.")
    @Test
    void createTest() {
        List<BowlingGameResult> bowlingGameResultList = Arrays.asList(
                new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT), new BowlingGameResult(NORMAL_STRIKE_FRAME_RESULT));

        BowlingGameResults bowlingGameResults = BowlingGameResults.of(bowlingGameResultList);

        assertThat(bowlingGameResults).isEqualTo(new BowlingGameResults(bowlingGameResultList));
    }
}
