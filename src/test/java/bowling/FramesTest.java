package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    @DisplayName("턴이 끝나지 않았으면 현재 프레임 번호를, 턴이 끝났으면 다음 프레임 번호를 출력한다.")
    void 다음_프레임_번호() {
        Frames notFinished = Frames.start(Pin.from(5));
        Frames finished = Frames.start(Pin.from(10));

        assertAll(
            () -> assertThat(notFinished.currentFrameNumber()).isEqualTo(1),
            () -> assertThat(finished.currentFrameNumber()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("턴이 끝났으면 다음 프레임이 시작한다")
    void 다음_프레임() {
        Frames finished = Frames.start(Pin.from(10));
        finished.bowl(Pin.from(2));
        assertThat(finished.currentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임까지 끝났음을 확인한다")
    void 마지막_프레임() {
        Frames finished = Frames.start(Pin.from(10));
        for (int i = 0; i < 11; i++) {
            finished.bowl(Pin.from(10));
        }
        assertThat(finished.isFinished()).isTrue();
    }
}
