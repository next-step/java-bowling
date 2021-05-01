package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerNameTest {
    @Test
    void 생성_테스트() {
        assertThat(PlayerName.of("LDH")).isEqualTo(PlayerName.of("LDH"));
    }

    @Test
    void 유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PlayerName.of("test"));
    }
}
