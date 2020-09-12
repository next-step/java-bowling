package camp.nextstep.edu.rebellion.bowling.domain;

import camp.nextstep.edu.rebellion.bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @DisplayName("참가자 이름이 3글자 이상일 경우 예외 발생")
    @Test
    public void playerNameLengthTest() {
        // given
        String name = "ABCDE";

        // when & then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름은 최대 3글자 입니다 " + name);
    }

    @DisplayName("참가지 이름이 영문자가 아닐 경우 예외 발생")
    @Test
    public void playerNameCharacterTest() {
        // given
        String name = "가1A";

        // when & then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름은 영문자만 가능합니다 " + name);
    }
}
