package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    @DisplayName("최초의 프레임은 1")
    void printCurrentFrame() {
        Board board = new Board();

        assertThat(board.getCurrentFrame()).isEqualTo(1);
    }
}
