package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class PlayerNameTest {
    @Test
    public void createTest() {
        assertThatCode(() -> PlayerName.valueOf("JSY"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "AB", "ABCD"})
    @DisplayName("플레이어 이름은 3글자로 제한한다.")
    public void invalidLengthTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> PlayerName.valueOf("ABCD"))
                .withMessage(PlayerName.INVALID_PLAYER_NAME_ERR_MSG);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123",  "12A", "!@#", "ABㄱ"})
    @DisplayName("플레이어 이름은 알파벳으로 제한한다.")
    public void invalidCharTest(String invalidInput) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> PlayerName.valueOf(invalidInput))
                .withMessage(PlayerName.INVALID_PLAYER_NAME_ERR_MSG);
    }
}
