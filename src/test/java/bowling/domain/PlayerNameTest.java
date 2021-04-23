package bowling.domain;

import bowling.exception.PlayerNameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNameTest {

    @Test
    @DisplayName("사용자의 이름을 입력받아 생성할 수 있다.")
    void create() {
        // given
        final String name = "KSD";

        // when
        final PlayerName playerName = new PlayerName(name);

        // then
        assertThat(playerName).isEqualTo(new PlayerName(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABCD"})
    @DisplayName("사용자의 이름은 1~3자여야 한다.")
    void playerNameLength(final String name) {
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(PlayerNameLengthException.class)
                .hasMessage(PlayerNameLengthException.PLAYER_NAME_RANGE_MESSAGE + name);
    }
}
