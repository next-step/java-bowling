package bowling.domain.frame;

import bowling.domain.PlayerTest;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @DisplayName("Frames Score(점수) 계산 테스트")
    @ParameterizedTest
    @MethodSource("makeScoreData")
    void getScore(List<Score> scores, int expectedResult) {
        Frames frames = Frames.init(PlayerTest.TEST_PLAYER);

        for (Score score : scores) {
            frames.bowl(score);
        }

        int actualResult = frames.getScore();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> makeScoreData() {
        return Stream.of(
                Arguments.of(Arrays.asList(Score.of("4"), Score.of("3"), Score.of("4"), Score.of("6")), 7),
                Arguments.of(Arrays.asList(Score.of("8"), Score.of("2"), Score.of("4"), Score.of("6")), 14),
                Arguments.of(Arrays.asList(Score.of("10"), Score.of("5"), Score.of("4")), 19),
                Arguments.of(Arrays.asList(Score.of("10"), Score.of("10"), Score.of("10"), Score.of("6")), 36)
        );
    }
}
