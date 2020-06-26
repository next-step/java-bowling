package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BasicFrame 테스트")
class BasicFrameTest {

    @DisplayName("다음 프레임 - 1~9프레임")
    @Test
    void nextFrame() {
        BasicFrame basicFrame = new BasicFrame(1);
        assertThat(basicFrame.nextFrame()).isInstanceOf(BasicFrame.class);
        assertThat(basicFrame.nextFrame().getFrameNumber()).isEqualTo(2);
    }

    @DisplayName("다음 프레임 - 10프레임")
    @Test
    void nextFrame_10frame() {
        BasicFrame basicFrame = new BasicFrame(9);
        assertThat(basicFrame.nextFrame()).isInstanceOf(FinalFrame.class);
        assertThat(basicFrame.nextFrame().getFrameNumber()).isEqualTo(10);
    }
}
