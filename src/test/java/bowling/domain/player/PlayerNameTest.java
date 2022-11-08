package bowling.domain.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerNameTest {

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "NAME"})
    void createFail_whenNameInvalid(String name) {
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 아니고 3자 이하이어야 합니다.");
    }
}
