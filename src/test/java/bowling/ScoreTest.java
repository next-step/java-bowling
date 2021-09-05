package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("두개의 스코어를 합한 결과를 반환")
    void sumTest() {
        Score score1 = new Score(1);
        Score score2 = new Score(1);

        assertThat(score1.sum(score2)).isEqualTo(new Score(2));
    }

//    @ParameterizedTest
//    @MethodSource("scoresAndExpectedResults")
//    @DisplayName("투구 점수로 부터 프레임의 결과를 결정")
//    void getFrameResultTest(Score score1, Score score2, FrameResult frameResult) {
//        assertThat(score1.getFrameResult(score2)).isEqualTo(frameResult);
//    }
//
//    static Stream<Arguments> scoresAndExpectedResults() {
//        return Stream.of(
//                Arguments.of(new Score(10), new Score(0), FrameResult.STRIKE),
//                Arguments.of(new Score(8), new Score(2), FrameResult.SPARE),
//                Arguments.of(new Score(7), new Score(2), FrameResult.MISS),
//                Arguments.of(new Score(0), new Score(0), FrameResult.MISS)
//        );
//    }
}
