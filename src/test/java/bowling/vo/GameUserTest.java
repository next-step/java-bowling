package bowling.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameUserTest {

    @DisplayName("사용자 이름에 한글이 들어가거나 3글자가 아니면 익셉션을 던진다")
    @ValueSource(strings = {"가나", "abcd", "ab", "가ab"})
    @ParameterizedTest
    void nameException(String name) {
        assertThatThrownBy(() -> new GameUser(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}