package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 플레이어 테스트")
class BowlingPlayerTest {

    @DisplayName("볼링 플레이어는 이름을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(BowlingPlayer.from("NOK")).isInstanceOf(BowlingPlayer.class);
    }

}
