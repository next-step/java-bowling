package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameIndexTest {

    @Test
    void create() {
        FrameIndex frameIndex = FrameIndex.create(1);
        assertThat(frameIndex.getIndex()).isEqualTo(1);
    }

    @Test
    void negative() {
        assertThatThrownBy(() -> FrameIndex.create(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
