package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class FramesTest {

    @Test
    void getFrames() {
        Frames frames = Frames.of(3);

        then(frames.getFrames().get(2).isLastFrame()).isTrue();
    }

    @Test
    void getCurrentFrame() {
    }

    @Test
    void bowl() {
    }

    @Test
    void isFinished() {
    }

    @Test
    void of() {
    }
}
