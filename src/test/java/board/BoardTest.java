package board;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void 보드생성하기() {
        String name = "PCI";
        Board board = Board.initBoard(name);

        assertThat(board).isEqualTo(new Board(name, new ArrayList<>()));
    }

    @Test
    void 다음_프레임번호_가져오기() {
        Board board = Board.initBoard("PCI");

        assertThat(board.getNowFrameNumber()).isEqualTo(1);
    }
}