package bowling2.domain;

import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import bowling2.exception.BowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        Frame firstFrame = new NormalFrame(1);
        assertThat(board).isEqualTo(new Board(List.of(firstFrame), firstFrame));
    }

    @DisplayName("쓰러트린 핀이 남은 핀을 넘으면 throw exp")
    @Test
    void handleAfterTry() {
        board.handleAfterTry(3);
        assertThatThrownBy(() -> board.handleAfterTry(8))
                .isInstanceOf(BowlingException.class);
    }
}
