package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    private Score createScore(int point) {
        return new Score(point);
    }

    @Test
    @DisplayName("0보다 작은 점수 입력시 Exception")
    void negativeNumberTest() {
        assertThatThrownBy(() -> this.createScore(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("10보다 큰 점수 입력시 Exception")
    void biggerThanTenTest() {
        assertThatThrownBy(() -> this.createScore(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("providePoint")
    @DisplayName("정상 점수 생성")
    void createScoreTest(int point) {
        Score score = this.createScore(point);
        assertThat(score.getPoint()).isEqualTo(point);
    }

    private static final Stream<Arguments> providePoint() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(8),
                Arguments.of(9),
                Arguments.of(10)
        );
    }

}