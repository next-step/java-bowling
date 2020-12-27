package bowling.domain.player;


import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-16 오전 7:53
 * Developer : Seo
 */
class PlayerTest {
    @DisplayName("사용자명 유효성 체크")
    @Test
    void invalid_name() {
        assertThatThrownBy(() -> new Player("a"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Player("123"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Player("가나다"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(new Player("ABC")).isNotNull().isInstanceOf(Player.class);
    }
}
