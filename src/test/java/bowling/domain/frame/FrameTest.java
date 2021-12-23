package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {


    @Test
    @DisplayName("해당 프레임이 Normal 인지 Final 인지 검증")
    void isNormalFrameTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.isNormalFrame()).isTrue();

        FinalFrame finalFrame = new FinalFrame(10);
        assertThat(finalFrame.isNormalFrame()).isFalse();
    }
}
