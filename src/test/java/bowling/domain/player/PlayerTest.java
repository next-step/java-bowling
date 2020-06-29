package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("플레이어의 이름이 3글자 알파벳이면 객체 생성 완료")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "ABC"})
    void of(String name) {
        assertThatCode(() -> Player.of(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름이 3글자 알파벳이 아니면 IllegalArgumentException")
    @ParameterizedTest
    @ValueSource(strings = {"123", "가나다", "aa1", "aa", "aaaa"})
    void of_name_not3LetterEnglish(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름");
    }
}
