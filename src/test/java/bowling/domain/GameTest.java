package bowling.domain;

import bowling.exception.InvalidNumberOfFallenPinsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {
    private final Game given_game = new Game();

    @Test
    void 게임_실행_비정상_핀갯수_초과() {
        assertThatThrownBy(() -> given_game.play(1, 11))
                .isInstanceOf(InvalidNumberOfFallenPinsException.class);
    }

    @Test
    void 게임_실행_정상() {
        assertThat(given_game.play(1, 2)).isEqualTo(2);
        assertThat(given_game.play(1, 7)).isEqualTo(7);

        assertThat(given_game.play(2, 5)).isEqualTo(5);
        assertThat(given_game.play(2, 5)).isEqualTo(5);
    }
}