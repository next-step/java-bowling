package bowling.domain.player;

import bowling.exception.PlayerNameNotValidException;
import bowling.exception.PlayerNameEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Player Test")
class PlayerTest {

    @Test
    @DisplayName("Player 생성")
    void generatePlayer() {
        //given
        String playerName = "홍길동";

        //when
        Player player = Player.valueOf(playerName);

        //then
        assertThat(player).isEqualTo(Player.valueOf(playerName));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 이름이 빈값일 경우 Exception")
    void generatePlayerNameEmptyException(String value) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Player.valueOf(value))
                .isInstanceOf(PlayerNameEmptyException.class);
    }

    @Test
    @DisplayName("Player 이름 길이가 유효하지 않을 경우 Exception")
    void generatePlayerNameException() {
        //given
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Player.NAME_LENGTH + 1; i++) {
            stringBuilder.append("A");
        }
        String givenName = stringBuilder.toString();

        // when
        // then
        assertThatThrownBy(() -> Player.valueOf(givenName))
                .isInstanceOf(PlayerNameNotValidException.class);
    }
}