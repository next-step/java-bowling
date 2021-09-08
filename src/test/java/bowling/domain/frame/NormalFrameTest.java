package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.score.Score;

class NormalFrameTest {

    private static Stream<Arguments> toScoreSymbolTest() {
        return Stream.of(
            Arguments.of(new int[] {0}, "-"),
            Arguments.of(new int[] {9}, "9"),
            Arguments.of(new int[] {10}, "X"),
            Arguments.of(new int[] {0, 1}, "-|1"),
            Arguments.of(new int[] {0, 10}, "-|/"),
            Arguments.of(new int[] {1, 2}, "1|2"),
            Arguments.of(new int[] {0, 2}, "-|2"),
            Arguments.of(new int[] {1, 0}, "1|-"),
            Arguments.of(new int[] {0, 0}, "-|-"),
            Arguments.of(new int[] {4, 6}, "4|/")
        );
    }

    @DisplayName("0과 10사이의 정수의 Scores객체를 입력 받으면, NormalFrame 객체를 생성한다.")
    @Test
    void createScoreTest() {
        assertThat(NormalFrame.of(Score.from(0))).isInstanceOf(NormalFrame.class);
        assertThat(NormalFrame.of(Score.from(10))).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("첫번째와 두번쨰 투구로 쓰러뜨린 핀의 합이 10을 벗어나면 예외를 던진다")
    @Test
    void exceptionTest() {
        Frame normalFrame = NormalFrame.of(Score.from(7));
        assertThatThrownBy(() -> normalFrame.addScore(Score.from(5))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쓰러뜨린 핀의 수가 다양하게 주어졌을 때, 그에 맞는 심볼을 출력한다")
    @ParameterizedTest
    @MethodSource
    void toScoreSymbolTest(int[] scores, String expected) {
        assertThat(NormalFrame.of(scores).toScoreSymbol()).isEqualTo(expected);
    }
}
