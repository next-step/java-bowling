package step4.domain;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

class FramesTest {
    Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();

        String[] splitPinCounts = "0 0 1 0 0 1 1 9 9 1 10 10 10 10 1 9 10".split(" ");
        while (!frames.isAllFinished()) {
            Arrays.stream(splitPinCounts)
                    .forEach(pinCount -> frames.throwBowl(pinCount));
        }
    }
}