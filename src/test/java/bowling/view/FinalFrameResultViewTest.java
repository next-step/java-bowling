package bowling.view;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameResultViewTest {
    @DisplayName("마지막 프레임, 3투구 모두 스트라이크의 경우 X|X|X로 표시한다.")
    @Test
    void finalPrint_allStrike() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(10);
        finalFrame.pitch(10);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X|X|X");
    }

    @DisplayName("마지막 프레임, 스페어 후 보너스 10핀의 경우 N|/|X로 표시한다.")
    @Test
    void finalPrint_spare_strike() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(8);
        finalFrame.pitch(2);
        finalFrame.pitch(10);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("8|/|X");
    }

    @DisplayName("마지막 프레임, 스페어 후 보너스 오픈의 경우 N|/|N로 표시한다.")
    @Test
    void finalPrint_spare_open() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(8);
        finalFrame.pitch(2);
        finalFrame.pitch(7);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("8|/|7");
    }

    @DisplayName("마지막 프레임, 스트라이크 후 스페어의 경우 X|N|/로 표시한다.(두번쩨 투구 0)")
    @Test
    void finalPrint_strike_spare_includeGutter() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(0);
        finalFrame.pitch(10);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X|-|/");
    }

    @DisplayName("마지막 프레임, 스트라이크 후 스페어의 경우 X|N|/로 표시한다.(두번째 투구 0 이외)")
    @Test
    void finalPrint_strike_spare() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(2);
        finalFrame.pitch(8);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X|2|/");
    }

    @DisplayName("마지막 프레임, 스트라이크 후 오픈의 경우 X|N|N로 표시한다.")
    @Test
    void finalPrint_strike_open() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(2);
        finalFrame.pitch(7);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X|2|7");
    }

    @DisplayName("마지막 프레임, 스트라이크 후 두번째 스트라이크 세번째 오픈의 경우 X|X|N로 표시한다.")
    @Test
    void finalPrint_strike_strike_open() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(10);
        finalFrame.pitch(10);
        finalFrame.pitch(8);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("X|X|8");
    }

    @DisplayName("마지막 프레임, 오픈의 경우 N|N로 표시한다.")
    @Test
    void finalPrint_noBonus() {
        ResultViewType resultViewType = new FinalFrameResultView();

        Frame finalFrame = new Frame(10);
        finalFrame.pitch(7);
        finalFrame.pitch(2);

        assertThat(resultViewType.frameResult(finalFrame)).isEqualTo("7|2");
    }
}
