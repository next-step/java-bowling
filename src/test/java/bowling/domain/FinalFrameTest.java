package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void 스트라이크3번() {
        FinalFrame frame = new FinalFrame()
            .bowl(Pins.of(10))
            .bowl(Pins.of(10))
            .bowl(Pins.of(10));

        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void 스페어_한번더() {
        Frame frame = new FinalFrame()
            .bowl(Pins.of(8))
            .bowl(Pins.of(2))
            .bowl(Pins.of(10));

        assertThat(frame.isFinished()).isTrue();
    }

    @Test
    void 스페어_스트라이크_아닐경우() {
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> new FinalFrame()
                .bowl(Pins.of(8))
                .bowl(Pins.of(1))
                .bowl(Pins.of(10))
            );
    }

    @Test
    void 세번째가_FirstBowl_일경우() {
        assertThat(new FinalFrame()
            .bowl(Pins.of(6))
            .bowl(Pins.of(4))
            .bowl(Pins.of(4))
            .isFinished()).isTrue();
    }

}