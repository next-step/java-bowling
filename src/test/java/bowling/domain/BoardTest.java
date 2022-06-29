package bowling.domain;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    void init() {
        assertThat(board.frames().size()).isEqualTo(0);
    }

    @Test
    void addFrameIfAbsent() {
        // given
        NormalFrame newFrame = new NormalFrame(1);

        // when, then
        board.addFrameIfAbsent(newFrame);
        assertThat(board.frames().size()).isEqualTo(1);
        board.addFrameIfAbsent(newFrame);
        assertThat(board.frames().size()).isEqualTo(1);
    }
}
