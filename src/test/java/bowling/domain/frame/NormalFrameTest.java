package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("9번 프레임에서 nextFrame 호출하면 FinalFrame 반환")
    @Test
    void nextFrame_WhenCalledAtMaxFrameNo_ReturnsFinalFrame() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.nextFrame(10)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest(name = "1~8번 프레임에서 nextFrame 호출하면 NormalFrame 반환")
    @ValueSource(ints = {1, 8})
    void nextFrame_WhenCalledNonMaxFrameNo_ReturnsNormalFrame(int frameNo) {
        NormalFrame frame = new NormalFrame(frameNo, 0);

        assertThat(frame.nextFrame(0)).isInstanceOf(NormalFrame.class);
    }
}
