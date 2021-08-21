package bowling.player;

import bowling.exception.IllegalPayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("플레이어 이름은 영문 3글자여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"플레이어", "한글", "세글자", "!@#", "123", "1234567", ""})
    void create_player(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalPayerNameException.class);
    }

}