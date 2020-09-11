package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseFrameTest {
    @Test
    void from() {
        assertThat(BaseFrame.from(1)).isNotNull();
    }

    @Test
    void getNumber() {
        assertThat(BaseFrame.from(1).getNumber()).isEqualTo(Frames.BEGIN_NUMBER);
    }

    @Test
    void hit() {
        assertThat(BaseFrame.from(1).hit(Frames.PIN_CLEAR_COUNT)).isEqualTo(Frames.PIN_COUNT);
        assertThat(BaseFrame.from(1).hit(Frames.PIN_COUNT)).isEqualTo(Frames.PIN_CLEAR_COUNT);
    }

    @Test
    void isClear() {
        Frame first = BaseFrame.from(1);
        first.hit(Frames.PIN_COUNT);

        assertThat(first.isClear()).isTrue();
    }
}
