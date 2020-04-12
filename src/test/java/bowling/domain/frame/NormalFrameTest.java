package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("프레임 객체 비교")
    void equalsToFrame() {
        NormalFrame normalFrame = new NormalFrame(1);
        NormalFrame expectedNormalFrame = new NormalFrame(1);

        boolean same = normalFrame.equals(expectedNormalFrame);

        assertThat(same).isTrue();
    }
}
