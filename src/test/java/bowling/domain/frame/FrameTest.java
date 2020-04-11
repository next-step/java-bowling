package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("Frame은 숫자를 가진다.")
    @Test
    void create() {
        int frameNumber = 1;
        Frame expect = new Frame(frameNumber);

        Frame actual = new Frame(frameNumber);

        assertThat(actual).isEqualTo(expect);
    }
}