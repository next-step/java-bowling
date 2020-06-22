package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {

    @ParameterizedTest
    @MethodSource("providePoint")
    @DisplayName("점수 더하기 테스트")
    void addPointTest(int firstPoint, int secondPoint, int totalScore) {
        Scores scores = new Scores();
        scores.addScore(firstPoint);
        scores.addScore(secondPoint);
        assertThat(scores.totalScore()).isEqualTo(totalScore);
    }

    private static final Stream<Arguments> providePoint() {
        return Stream.of(
                Arguments.of(1, 3, 4),
                Arguments.of(2, 4, 6),
                Arguments.of(5, 3, 8)
        );
    }
}