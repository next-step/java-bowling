package bowling.step2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void 이름이_3글자이상으로_입력되었을_때() {
        Assertions.assertThatThrownBy(() -> new Player("ryan"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.NAME_LENGTH_EXCEPTION);
    }


    @Test
    void 이름이_영어가아닌문자로_입력되었을_때() {
        Assertions.assertThatThrownBy(() -> new Player("라이언"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.NAME_ENGLISH_EXCEPTION);
    }

    @Test
    void 이름이_빈값일_때() {
        Assertions.assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.NAME_BLANK_EXCEPTION);
    }
}