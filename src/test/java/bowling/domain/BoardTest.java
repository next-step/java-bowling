package bowling.domain;

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
        assertThat(board.frames().size()).isEqualTo(10);
    }

    @Test
    void indexIsGreaterThan10Given_ReturnFrame() {
        assertThat(board.frame(11).equal(11)).isTrue();
    }
}
