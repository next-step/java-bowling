package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    private static Stream<Arguments> provideScoresAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(new Score(1, 0), new Score(2, 0), new Score(3, 0)),
                        Arrays.asList(1, 3, 6)
                )
        );
    }

    @DisplayName("각 프레임의 점수를 이전 프레임 점수에 더해서 리스트로 반환한다.")
    @ParameterizedTest
    @MethodSource("provideScoresAndExpectedResult")
    void getFrameScores(List<Score> scores, List<Integer> expectedResult) {
        Scores frameScores = new Scores(scores);

        assertThat(frameScores.getFrameScores()).isEqualTo(expectedResult);
    }
}