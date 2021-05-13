package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerCountTest {

    @DisplayName("PlayerCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        int count = 10;

        // when
        PlayerCount playerCount = new PlayerCount(count);

        // then
        assertThat(playerCount).isNotNull();
    }
}