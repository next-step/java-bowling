package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThatCode(() -> Player.of("PEJ")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름은 3 글자까지 허용")
    void valid() {
        assertThatThrownBy(() -> Player.of("PEAA"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
