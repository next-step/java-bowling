package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class PlayerTest {

    @Test
    void shouldValidatePlayer() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Player(null, null));
    }

}
