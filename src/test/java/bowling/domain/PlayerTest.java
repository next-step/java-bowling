package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("플레이어를 생성한다")
    @Test
    void initTest() {
        String name = "bao";
        Player player = Player.from(name);
        assertThat(player.name()).isEqualTo(name);
    }

    @DisplayName("플레이어의 이름은 3글자를 초과할 수 없다")
    @Test
    void initExceptionTest() {
        assertThatThrownBy(() -> Player.from("pandahun"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}