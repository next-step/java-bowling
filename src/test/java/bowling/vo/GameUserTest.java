package bowling.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameUserTest {

    @DisplayName("영문 세글자가아니면 익셉션")
    @ValueSource(strings = {"ab", "가나다", "a가나다", "abcd"})
    @ParameterizedTest
    void user(String name) {
        assertThatThrownBy(() -> new GameUser(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}