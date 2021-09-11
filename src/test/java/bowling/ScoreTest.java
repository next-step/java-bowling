package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("10 점 이상 입력시 예외 발생")
    void scoreTest() {
        assertThatThrownBy(() -> new Score(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두개의 스코어를 합한 결과를 반환")
    void sumTest() {
        Score score1 = new Score(1);
        Score score2 = new Score(1);

        assertThat(score1.sum(score2)).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("점수를 문자열로 변환")
    void getScoreStringTest(int given, String expected) {
        assertThat(new Score(given).getScoreString()).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(0, "0"),
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(7, "7"),
                Arguments.of(8, "8"),
                Arguments.of(9, "9")
        );
    }
}
