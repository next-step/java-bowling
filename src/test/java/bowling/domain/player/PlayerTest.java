package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("플레이어는 길이 문자열을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Player.from("NOK")).isInstanceOf(Player.class);
    }

}
