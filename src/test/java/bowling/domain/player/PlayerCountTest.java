package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerCountTest {

    @Test
    void 생성_테스트() {
        // given
        PlayerCount count = PlayerCount.of(3);
        // when & then
        assertThat(count).isEqualTo(PlayerCount.of("3"));
        assertThat(count.toInteger()).isEqualTo(3);
    }

    @Test
    void 유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PlayerCount.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> PlayerCount.of("ldh"));
    }
}
