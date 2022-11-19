package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void 턴이_끝났는지_확인() {
        Frames firstFrame = Frames.start();
        firstFrame.bowl(Pin.from(5));
        assertThat(firstFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("턴이 끝났으면 다음 프레임이 시작한다")
    void 다음_프레임() {
        Frames firstFrame = Frames.start();
        firstFrame.bowl(Pin.from(10));
        assertThat(firstFrame.currentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임까지 끝났음을 확인한다")
    void 마지막_프레임() {
        Frames finished = Frames.start();
        for (int i = 0; i < 12; i++) {
            finished.bowl(Pin.from(10));
        }
        assertThat(finished.isFinished()).isTrue();
    }
}
