package bowling.domain.player;

import static bowling.domain.player.Player.MAX_LENGTH;
import static bowling.domain.player.Player.MAX_NAME_LENGTH;
import static bowling.domain.player.Player.NAME_SHOULD_BE_ALPHABET;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 이름길이_조건_테스트() {
        String given = "TESTNAME";
        assertThatThrownBy(() -> new Player(given))
            .isInstanceOf(BowlingException.class)
            .hasMessage(String.format(MAX_NAME_LENGTH, MAX_LENGTH));
    }

    @Test
    void 영문이름_조건_테스트() {
        String given = "테스트";
        assertThatThrownBy(() -> new Player(given))
            .isInstanceOf(BowlingException.class)
            .hasMessage(NAME_SHOULD_BE_ALPHABET);
    }
}