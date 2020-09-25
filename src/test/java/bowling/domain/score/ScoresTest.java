package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoresTest {

    @DisplayName("프레임 점수 추가 테스트")
    @ParameterizedTest
    @MethodSource("pins")
    void addScoreTest(List<Integer> values) {
        Scores scores = new Scores();

        for (Integer value : values) {
            scores.add(Score.defaultScore(scores, value));
        }

        int currentPoint = values.stream()
                .mapToInt(Integer::intValue)
                .sum();

        assertThat(scores.currentPinCount()).isEqualTo(currentPoint);
    }

    static Stream<Arguments> pins() {
        return Stream.of(
                arguments(Arrays.asList(1, 2)),
                arguments(Arrays.asList(4, 5))
        );
    }

    @DisplayName("스트라이크 확인 테스트")
    @Test
    void isStrikeTest() {
        Scores scores = new Scores();
        scores.add(Score.defaultScore(scores, 10));
        assertThat(scores.isStrike(0)).isTrue();
    }

    @DisplayName("스페어 확인 테스트")
    @Test
    void isSpareTest() {
        Scores scores = new Scores();
        scores.add(Score.defaultScore(scores, 1));
        scores.add(Score.defaultScore(scores, 9));
        assertThat(scores.isSpare(1)).isTrue();
    }

}