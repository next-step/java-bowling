package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BowlingScoreTest {

    @DisplayName("점수 생성 실패 - 최소값 이하 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, -3, -100})
    void create_fail_under_minimum(int score) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new BowlingScore(score);
        });
    }

    @DisplayName("점수 생성 실패 - 최대값 초과 테스트")
    @ParameterizedTest
    @ValueSource(ints = {11, 15, 100})
    void create_fail_exceed_maximum(int score) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new BowlingScore(score);
        });
    }

    @DisplayName("최대 점수 테스트")
    @Test
    void isPerfect_test() {
        // given
        BowlingScore bowlingScore = new BowlingScore(10);

        // when & then
        assertThat(bowlingScore.isPerfect()).isTrue();
    }

    @DisplayName("점수 합산 테스트")
    @ParameterizedTest
    @MethodSource("provideScores")
    void add(BowlingScore bowlingScore, BowlingScore bowlingScore2) {
        // when
        BowlingScore result = bowlingScore.add(bowlingScore2);

        // then
        assertThat(result.isPerfect()).isTrue();

    }

    private static Stream<Arguments> provideScores() {
        return Stream.of(
                Arguments.of(new BowlingScore(1), new BowlingScore(9)),
                Arguments.of(new BowlingScore(5), new BowlingScore(5)),
                Arguments.of(new BowlingScore(4), new BowlingScore(6)),
                Arguments.of(new BowlingScore(8), new BowlingScore(2)),
                Arguments.of(new BowlingScore(3), new BowlingScore(7))
        );
    }
}

