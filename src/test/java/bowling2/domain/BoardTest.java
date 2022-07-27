package bowling2.domain;

import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import bowling2.exception.BowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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

    @DisplayName("투구가 끝나면 새로운 프레임은 보드에 추가된다.")
    @Test
    void moveToNextFrame() {
        assertThat(board.frameSize()).isEqualTo(1);
        board.handleAfterTry(3);
        board.handleAfterTry(4);
        assertThat(board.frameSize()).isEqualTo(2);
    }

    @DisplayName("투구가 안 끝나면 새로운 프레임은 보드에 추가되지 않는다.")
    @Test
    void notMoveToNextFrame() {
        assertThat(board.frameSize()).isEqualTo(1);
        board.handleAfterTry(3);
        assertThat(board.frameSize()).isEqualTo(1);
    }


    @DisplayName("인덱스에 해당하는 프레임을 찾는데 성공한다.")
    @Test
    void findPendingFrame() {
        board = new Board(List.of(new NormalFrame(1, 0, List.of(1, 9))));

        assertThat(board.search(1).getIndex()).isEqualTo(1);
    }

    @DisplayName("인덱스에 해당하는 프레임이 없어서 exp")
    @Test
    void findPendingFrame_fail() {
        board = new Board(Collections.emptyList());

        assertThatThrownBy(() -> board.search(1))
                .isInstanceOf(BowlingException.class);
    }
}
