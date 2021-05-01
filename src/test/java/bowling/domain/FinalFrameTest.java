package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {
    @Test
    @DisplayName("1구째에 스트라이크가 되면, 2번 더 기회가 제공된다.")
    void finalFrameBonusGameIfGotStrikeTest() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThat(finalFrame.inputScore(6)).isEqualTo("6");
        assertThatThrownBy(() -> finalFrame.inputScore(4))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("2구째에 스페어가 되면, 1번 더 기회가 제공된다.")
    void finalFrameBonusGameIfGotSpareTest() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.inputScore(6)).isEqualTo("6");
        assertThat(finalFrame.inputScore(4)).isEqualTo("/");
        assertThat(finalFrame.inputScore(5)).isEqualTo("5");
        assertThatThrownBy(() -> finalFrame.inputScore(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("2구째에 스페어가 되고, 스트라이크를 한 경우에도 1번만 가능하다.")
    void finalFrameBonusGameIfGotStrikeAfterSpareTest() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.inputScore(6)).isEqualTo("6");
        assertThat(finalFrame.inputScore(4)).isEqualTo("/");
        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThatThrownBy(() -> finalFrame.inputScore(10))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("1구째 스트라이크, 총 3연속 스트라이크")
    void finalFrameBonusGameIfThreeStrikeTest() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThat(finalFrame.inputScore(10)).isEqualTo("X");
        assertThatThrownBy(() -> finalFrame.inputScore(10))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("2구안에 스트라이크/스페어를 못하면, 일반적인 2회 처리로 한다.")
    void finalFrameNoBonusGameTest() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame.inputScore(6)).isEqualTo("6");
        assertThat(finalFrame.inputScore(2)).isEqualTo("2");
        assertThatThrownBy(() -> finalFrame.inputScore(2))
                .isInstanceOf(IllegalStateException.class);
    }
}
