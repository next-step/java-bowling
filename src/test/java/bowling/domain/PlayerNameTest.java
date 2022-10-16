package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PlayerNameTest {
    @Test
    @DisplayName("이름이 3글자 초과면 IllegalArgumentException")
    void length_greater_than_3() {
        //given
        String name = "apple";
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new PlayerName(name));
    }

    @Test
    @DisplayName("이름이 3글자 이하면 정상")
    void length_less_than_3() {
        //given
        String name = "app";
        //then
        assertThatNoException().isThrownBy(() -> new PlayerName(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a#B", "%b^", "ab ", "a b", "1ab"})
    @DisplayName("이름에 알파벳이 아닌 다른 글자가 들어가면 IllegalArgumentException")
    void is_alphabet(String name) {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new PlayerName(name));
    }


}