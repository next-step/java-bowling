package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class FramesTest {

    @Test
    @DisplayName("경기 종료 여부 검증")
    void isFinished() {
        Frames frames = Frames.of(2);

        frames.bowl(Pin.ofMin());
        frames.bowl(Pin.ofMin());
        frames.bowl(Pin.ofMin());
        frames.bowl(Pin.ofMin());

        then(frames.isFinished()).isTrue();
    }
}
