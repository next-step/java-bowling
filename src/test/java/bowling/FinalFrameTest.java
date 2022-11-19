package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("마지막 프레임은 스트라이크나 스페어를 치면 보너스 기회가 주어진다.")
    void 마지막_프레임_종료() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(10));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(30);
    }

    @Test
    void 마지막_프레임의_다음_프레임은_에러() {
        FinalFrame finalFrame = FinalFrame.start();
        assertThatThrownBy(finalFrame::nextFrame)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 스트라이크_스트라이크_스트라이크() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(10));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(30);
    }

    @Test
    void 스트라이크_스트라이크_미스() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(1));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(21);
    }

    @Test
    void 스트라이크_스페어() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(10));
        start.bowl(Pin.from(1));
        start.bowl(Pin.from(9));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(20);
    }

    @Test
    void 스페어_미스() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(1));
        start.bowl(Pin.from(9));
        start.bowl(Pin.from(1));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(11);
    }

    @Test
    void 미스() {
        FinalFrame start = FinalFrame.start();
        start.bowl(Pin.from(4));
        start.bowl(Pin.from(1));

        assertThat(start.isFinished()).isTrue();
        assertThat(start.getScores().getScore()).isEqualTo(5);
    }
}
