package bowling.view;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameResultViewTest {
    @DisplayName("스트라이크는 X로 표시한다.")
    @Test
    void normal_strike() {
        ResultViewType resultViewType = new NormalFrameResultView();

        Frame finalFrame = new Frame(1);
        finalFrame.pitch(10);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X");
    }

    @DisplayName("스페어는 /로 표시한다.")
    @Test
    void normal_spare() {
        ResultViewType resultViewType = new NormalFrameResultView();

        Frame finalFrame = new Frame(1);
        finalFrame.pitch(6);
        finalFrame.pitch(4);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("6|/");
    }

    @DisplayName("오픈 프레임은 N|N으로 표시한다.")
    @Test
    void normal_open() {
        ResultViewType resultViewType = new NormalFrameResultView();

        Frame finalFrame = new Frame(1);
        finalFrame.pitch(0);
        finalFrame.pitch(4);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("-|4");
    }
}
