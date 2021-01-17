package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("MISS 이후 2프레임으로 이동 ")
    void secondFrameAfterMiss() {
        Board board = new Board();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(2);
    }
}
