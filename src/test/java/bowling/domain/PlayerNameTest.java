package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class PlayerNameTest {
    @Test
    public void createTest() {
        assertThatCode(() -> PlayerName.valueOf("JSY"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 이름은 3글자로 제한한다.")
    public void invalidLengthTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> PlayerName.valueOf("ABCD"))
                .withMessage(PlayerName.INVALID_PLAYER_NAME_ERR_MSG);
    }

    @Test
    @DisplayName("플레이어 이름은 알파벳으로 제한한다.")
    public void invalidCharTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> PlayerName.valueOf("123"))
                .withMessage(PlayerName.INVALID_PLAYER_NAME_ERR_MSG);
    }
}
