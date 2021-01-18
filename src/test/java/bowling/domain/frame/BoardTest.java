package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("최초의 프레임은 1")
    void printCurrentFrame() {
        Board board = new Board();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크 이후 2프레임으로 이동")
    void secondFrameAfterStrike() {
        Board board = new Board();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(10);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("MISS 이후 2프레임으로 이동")
    void secondFrameAfterMiss() {
        Board board = new Board();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(2);
    }

    private static Stream<Arguments> gameScenario() {
        return Stream.of(
                Arguments.of(new int[]{
                        10,
                        9, 1,
                        8, 2,
                        10,
                        10,
                        4, 5,
                        10,
                        10,
                        10,
                        10, 10, 10
                }),
                Arguments.of(new int[]{
                        5, 5,
                        6, 4,
                        10,
                        3, 4,
                        4, 5,
                        10,
                        10,
                        10,
                        8, 1,
                        6, 3
                })
        );
    }

    @MethodSource("gameScenario")
    @ParameterizedTest
    @DisplayName("게임 종료조건 테스트")
    void isEnd(int[] scenario) {
        Board board = new Board();

        for (int downedPin : scenario) {
            assertThat(board.isEnd()).isFalse();
            board.record(downedPin);
        }

        assertThat(board.isEnd()).isTrue();
    }
}
