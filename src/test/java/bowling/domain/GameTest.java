package bowling.domain;

import bowling.exception.InvalidNumberOfFallenPinsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {
    private final Game given_game = new Game();
    private final Record given_record = new Record();

    @Test
    void 게임_실행_비정상_핀갯수_초과() {
        assertThatThrownBy(() -> given_game.play(1, 11))
                .isInstanceOf(InvalidNumberOfFallenPinsException.class);
    }

    @Test
    void 게임_실행_정상() {
        assertThat(given_game.play(1, 2)).isEqualTo(8);
        assertThat(given_game.play(1, 7)).isEqualTo(1);

        assertThat(given_game.play(2, 5)).isEqualTo(5);
        assertThat(given_game.play(2, 5)).isEqualTo(0);
    }

    @Test
    void 게임_기록_저장() {
        assertThat(given_game.play(1, 2)).isEqualTo(8);
        given_game.record(1, given_record);
        assertThat(given_record.getRecords().get(0)).isEqualTo(2);

        assertThat(given_game.play(1, 7)).isEqualTo(1);
        given_game.record(1, given_record);
        assertThat(given_record.getRecords().get(1)).isEqualTo(9);

        assertThat(given_game.play(2, 5)).isEqualTo(5);
        given_game.record(2, given_record);
        assertThat(given_record.getRecords().get(2)).isEqualTo(5);

        assertThat(given_game.play(2, 5)).isEqualTo(0);
        given_game.record(2, given_record);
        assertThat(given_record.getRecords().get(3)).isEqualTo(10);
    }
}