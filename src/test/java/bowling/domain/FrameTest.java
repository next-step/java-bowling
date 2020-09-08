package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @Test
    void from() {
        assertThat(Frame.from(Frame.BEGIN_STAGE)).isNotNull();
    }

    @Test
    void fromThrowException() {
        assertThatThrownBy(() -> Frame.from(Frame.BEGIN_STAGE - 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Frame.from(Frame.END_STAGE + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void record() {
        Frame frame = Frame.from(Frame.BEGIN_STAGE);
        int count = 0;

        while (frame.hasNextTurn()) {
            frame.record(0);
            count++;
        }

        assertThat(count).isEqualTo(2);
    }

    @Test
    void recordWithBonusStep() {
        Frame frame = Frame.from(Frame.END_STAGE);
        int count = 0;

        while (frame.hasNextTurn()) {
            frame.record(count == 0 ? 0 : 10);
            count++;
        }

        assertThat(count).isEqualTo(3);
    }
}
