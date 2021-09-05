package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    private static Stream<Arguments> toScoreSymbolTest() {
        return Stream.of(
            Arguments.of(new int[]{1}, "1"),
            Arguments.of(new int[]{0}, "-"),

            Arguments.of(new int[]{1, 2}, "1|2"),
            Arguments.of(new int[]{0, 0}, "-|-"),
            Arguments.of(new int[]{0, 2}, "-|2"),
            Arguments.of(new int[]{1, 0}, "1|-"),
            Arguments.of(new int[]{4, 6, 0}, "4|/|-"),

            Arguments.of(new int[]{4, 6}, "4|/"),
            Arguments.of(new int[]{4, 6, 10}, "4|/|X"),
            Arguments.of(new int[]{10, 4, 6}, "X|4|/"),

            Arguments.of(new int[]{10, 10, 0}, "X|X|-"),
            Arguments.of(new int[]{10, 0, 10}, "X|-|/"),
            Arguments.of(new int[]{10, 0, 0}, "X|-|-"),
            Arguments.of(new int[]{0, 10, 0}, "-|/|-"),
            Arguments.of(new int[]{0, 10, 6}, "-|/|6"),
            Arguments.of(new int[]{10}, "X"),
            Arguments.of(new int[]{10, 10}, "X|X"),
            Arguments.of(new int[]{10, 10, 10}, "X|X|X")
        );
    }

    @DisplayName("0과 10사이의 정수의 Scores객체를 입력 받으면, NormalFrame 객체를 생성한다")
    @Test
    void createScoreTest() {
        assertAll(
            () -> assertThat(FinalFrame.of(Score.from(0))).isInstanceOf(FinalFrame.class),
            () -> assertThat(FinalFrame.of(Score.from(5))).isInstanceOf(FinalFrame.class),
            () -> assertThat(FinalFrame.of(Score.from(10))).isInstanceOf(FinalFrame.class)
        );
    }

    @DisplayName("첫번째 투구가 스트라이크가 아니고, 첫번째와 두번쨰 투구로 쓰러뜨린 핀의 합이 10을 벗어나면 예외를 던진다")
    @Test
    void exceptionTest1() {
        Frame finalFrame = FinalFrame.of(Score.from(5));
        assertThatThrownBy(() -> finalFrame.addScore(Score.from(9))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 투구가 스트라이크이고, 두번째와 세번쨰 투구로 쓰러뜨린 핀의 합이 10을 벗어나면, 예외를 던진다")
    @Test
    void exceptionTest2() {
        Frame finalFrame = FinalFrame.of(Score.from(10));
        finalFrame.addScore(Score.from(5));
        assertThatThrownBy(() -> finalFrame.addScore(Score.from(9))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쓰러뜨린 핀의 수가 다양하게 주어졌을 때, 그에 맞는 심볼을 출력한다")
    @ParameterizedTest
    @MethodSource
    void toScoreSymbolTest(int[] scores, String expected) {
        assertThat(FinalFrame.of(scores).toScoreSymbol()).isEqualTo(expected);
    }
}
