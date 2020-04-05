package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 객체 비교")
    void equalsToFrame() {
        Frame frame = new Frame(1);
        Frame expectedFrame = new Frame(1);

        boolean same = frame.equals(expectedFrame);

        assertThat(same).isTrue();
    }
}
