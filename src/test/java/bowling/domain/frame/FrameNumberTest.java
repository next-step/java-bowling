package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class FrameNumberTest {

    @Test
    void create() {
        FrameNumber frameNumber = FrameNumber.create(1);

        assertThat(frameNumber.getValue()).isEqualTo(1);
    }

    @Test
    void minus_number() {
        assertThatThrownBy(() -> FrameNumber.create(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
