package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 생성_테스트() {
        assertThat(Player.of("LDH")).isEqualTo(Player.of("LDH"));
    }

    @Test
    void 유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Player.of("test"));
    }
}
