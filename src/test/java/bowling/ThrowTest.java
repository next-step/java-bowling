package bowling;

import org.junit.jupiter.api.Test;

public class ThrowTest {
    @Test
    void create() {
        new Throw(0);
    }

    private static class Throw {
        public Throw(int fallingPins) {
        }
    }
}
