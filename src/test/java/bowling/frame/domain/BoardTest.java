package bowling.frame.domain;

import bowling.pin.domain.Pin;
import bowling.player.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("이전 프레임이 종료 되면 새로운 프레임을 생성한다.")
    void roll() {
        Board board = Board.init(Player.of("PSD"));
        board.roll(Pin.of(10));
        assertThat(board.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 프레임이 종료 되면 게임이 종료 된다.")
    void isGameOver() {
        Board board = Board.init(Player.of("PSD"));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        board.roll(Pin.of(10));
        assertThat(board.isGameOver()).isTrue();
    }
}
