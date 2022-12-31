package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "ab"})
    void 생성_invalid(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
