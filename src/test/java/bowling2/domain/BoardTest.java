package bowling2.domain;

import bowling2.domain.frame.Frame2;
import bowling2.domain.frame.NormalFrame2;
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
        Frame2 firstFrame = new NormalFrame2(1);
        assertThat(board).isEqualTo(new Board(List.of(firstFrame), firstFrame));
    }
}
