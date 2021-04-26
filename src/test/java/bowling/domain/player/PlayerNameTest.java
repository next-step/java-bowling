package bowling.domain.player;

import bowling.exception.NotAllowedPlayerNameCharacterException;
import bowling.exception.PlayerNameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"KSD", "ksd"})
    @DisplayName("사용자의 이름을 입력받아 생성할 수 있다.")
    void create(final String name) {
        // given
        // when
        final PlayerName playerName = PlayerName.valueOf(name);

        // then
        assertThat(playerName).isEqualTo(PlayerName.valueOf(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABCD"})
    @DisplayName("사용자의 이름은 1~3자여야 한다.")
    void playerNameLength(final String invalidName) {
        assertThatThrownBy(() -> PlayerName.valueOf(invalidName))
                .isInstanceOf(PlayerNameLengthException.class)
                .hasMessage(PlayerNameLengthException.PLAYER_NAME_RANGE_MESSAGE + invalidName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"김선동", "123"})
    @DisplayName("사용자의 이름으로 사용될 수 있는 문자는 영문 대소문자만 허용된다.")
    void playerNameCharacter(final String invalidName) {
        assertThatThrownBy(() -> PlayerName.valueOf(invalidName))
                .isInstanceOf(NotAllowedPlayerNameCharacterException.class)
                .hasMessage(NotAllowedPlayerNameCharacterException.NOT_ALLOWED_PLAYER_NAME_CHARACTER + invalidName);
    }
}
