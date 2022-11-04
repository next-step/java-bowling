package bowling.view;

import bowling.domain.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTest {

    @DisplayName("마지막 프레임에서 3개 스트라이크인 경우")
    @Test
    void finalFrame() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        FrameResult result = new FrameResult();

        assertThat(result.frameSign(finalFrame)).contains("X|X|X");
    }
}
