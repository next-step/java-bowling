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
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoresTest {
    @DisplayName("프레임 점수 생성")
    @Test
    void create() {
        assertThatCode(() -> new Scores());
    }

    @DisplayName("프레임 점수 저장")
    @ParameterizedTest
    @MethodSource("points")
    void addScore(List<Integer> values) {
        Scores scores = new Scores();
        for (Integer value : values) {
            scores.add(Score.defaultFrameScore(scores, value));
        }

        int currentPoint = values.stream()
                .mapToInt(Integer::intValue)
                .sum();

        assertThat(scores.currentPoint()).isEqualTo(currentPoint);
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(1, 2)),
                arguments(Arrays.asList(4, 5))
        );
    }

    @DisplayName("초구 스트라이크 확인")
    @Test
    void isStrike() {
        Scores socres = strikeScores();

        assertThat(socres.isStrike(0)).isTrue();
    }

    private Scores strikeScores() {
        Scores scores = new Scores();
        scores.add(Score.defaultFrameScore(scores, 10));

        return scores;
    }

    @DisplayName("스페어 확인")
    @Test
    void isSpare() {
        Scores socres = spareScores();

        assertThat(socres.isSpare(1)).isTrue();
    }

    private Scores spareScores() {
        Scores scores = new Scores();
        scores.add(Score.defaultFrameScore(scores, 1));
        scores.add(Score.defaultFrameScore(scores, 9));

        return scores;
    }
}