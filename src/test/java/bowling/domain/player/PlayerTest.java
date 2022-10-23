package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("플레이어의 이름이 같은 경우 동일한 플레이어이다.")
    @Test
    void equals() {
        assertThat(new Player("abc")).isEqualTo(new Player("abc"));
    }
}
