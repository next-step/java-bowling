package bowling.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    void initializeFirstFrame_success() {
        // when
        Frame init = Frame.initialize();

        // then
        assertThat(init).isNotNull();
        assertThat(init.getIndex()).isEqualTo(1);
    }

    @Test
    void bowl_firstBowl_thenCurrentFrame() {
        // given
        Pins pins = Pins.DOWN_ZERO;

        // when
        Frame init = Frame.initialize().bowling(pins);

        // then
        assertThat(init).isNotNull();
        assertThat(init.getIndex()).isEqualTo(1);
    }
}
