package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {
    @DisplayName("볼링 유저 생성")
    @Test
    void create() {
        assertThatCode(() -> new Player("LDC"));
    }

    @DisplayName("이름이 3글자가 아닌 경우 생성 실패")
    @Test
    void createFailByNameLength() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player("LDCD"));
    }
}
