package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("마지막 프레임은 스트라이크나 스페어를 치면 보너스 기회가 주어진다.")
    void 마지막_프레임_종료() {
        FinalFrame strike = FinalFrame.start(Pin.from(10));

        FinalFrame spare = FinalFrame.start(Pin.from(1));
        spare.bowl(Pin.from(9));

        FinalFrame miss = FinalFrame.start(Pin.from(1));
        miss.bowl(Pin.from(3));

        assertAll(
            () -> assertFalse(strike.isFinished()),
            () -> assertFalse(spare.isFinished()),
            () ->assertTrue(miss.isFinished())
        );
    }

    @Test
    void 마지막_프레임의_다음_프레임은_에러() {
        FinalFrame finalFrame = FinalFrame.start(Pin.from(10));
        assertThatThrownBy(() -> finalFrame.nextFrame(Pin.from(10)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 첫번째_스트라이크이면_두번째는_새로운_핀() {
        FinalFrame finalFrame = FinalFrame.start(Pin.from(10));
        finalFrame.bowl(Pin.from(10));

        assertAll(
            () -> assertThat(finalFrame.getScores().getScore(0)).isEqualTo(10),
            () -> assertThat(finalFrame.getScores().getScore(1)).isEqualTo(10)
        );
    }
}
