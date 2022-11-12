package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class PlayerTest {
    @Test
    void 생성() {
        assertThatNoException().isThrownBy(() -> new Player("abc"));
    }
}
