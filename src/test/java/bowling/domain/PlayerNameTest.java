package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerNameTest {

    @Test
    void create() {
        assertThat(PlayerName.from("PSY")).isEqualTo(PlayerName.from("PSY"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyPlayer(String playerName) {
        assertThatThrownBy(() -> PlayerName.from(playerName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void invalidPlayerName() {
        assertAll(
                () -> assertThatThrownBy(() -> PlayerName.from("PS")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> PlayerName.from("BOWLING")).isInstanceOf(IllegalArgumentException.class)
        );
    }
}