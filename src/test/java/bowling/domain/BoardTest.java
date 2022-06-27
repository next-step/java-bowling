package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertThat(board.frames().size()).isEqualTo(0);
    }

    @Test
    void indexIsGreaterThan10Given_ThrowExp() {
        assertThatThrownBy(() -> board.frame(11))
                .hasMessageContaining("찾으려는 index의 frame이 없습니다.");
    }
}
