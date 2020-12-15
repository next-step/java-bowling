package bowling;

import org.junit.jupiter.api.Test;

public class BallThrowTest {
    @Test
    void create() {
        new BallThrow(0);
    }

    private static class BallThrow {
        public BallThrow(int fallingPins) {
        }
    }
}
