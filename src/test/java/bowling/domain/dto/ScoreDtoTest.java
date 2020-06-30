package bowling.domain.dto;

import bowling.domain.score.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ScoreDtoTest {

    @DisplayName("ScoreDto 생성")
    @Test
    public void create() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ScoreDto.of(Score.ofStrike()));
    }

    @DisplayName("볼링 핀의 개수를 반환")
    @ParameterizedTest
    @MethodSource
    void getScore(final Score score, final int expected) {
        assertThat(score.getScore())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getScore() {
        return Stream.of(
                Arguments.of(Score.ofMiss(3), 3),
                Arguments.of(Score.valueOf(2, 0), 2)
        );
    }

    @DisplayName("볼링 핀의 개수를 반환 실패")
    @ParameterizedTest
    @MethodSource
    void getScoreFailure(final Score score) {
        assertThatIllegalArgumentException()
                .isThrownBy(score::getScore);
    }

    private static Stream<Arguments> getScoreFailure() {
        return Stream.of(
                Arguments.of(Score.ofStrike()),
                Arguments.of(Score.ofSpare()),
                Arguments.of(Score.UN_SCORE)
        );
    }
}
