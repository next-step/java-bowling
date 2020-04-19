package seul.bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import seul.bowling.domain.status.Miss;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    void player() {
        String name = "PES";

        Player player = new Player(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    void player_exception() {
        String name = "PESSS";

        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_NAME_LENGTH.getErrorMessage());
    }

    @Test
    void play() {
        Player player = new Player("PES");

        player.play(8);
        player.play(1);

        assertThat(player.getFrames().getFrames()).hasSize(1);
        assertThat(player.getFrames().getFrames().get(0).getStatus().getClass()).isEqualTo(Miss.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "9:false"}, delimiter = ':')
    void isEnd(int value, boolean expected) {
        Player player = new Player("PES");

        for (int i = 0; i < value; i++) {
            player.play(8);
            player.play(1);
        }

        assertThat(player.isEnd()).isEqualTo(expected);
    }
}
