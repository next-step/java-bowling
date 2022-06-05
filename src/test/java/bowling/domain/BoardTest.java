package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void indexGiven_ReturnExp() {
        assertThatThrownBy(() -> board.frame(11))
                .isInstanceOf(BowlingException.class);
    }

    @Test
    void frameIndexAndScoreGiven_SuccessAddScore() {
        board.addScore(1, 3);
        assertThat(board.frame(1).scores()).isEqualTo(List.of(3));
    }
}
