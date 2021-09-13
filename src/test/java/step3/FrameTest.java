package step3;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.state.FirstBowl;
import step3.state.Strike;

class FrameTest {
    @DisplayName("strike 상태 확인")
    @Test
    void strikeState() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(10);
        Assertions.assertThat(frame.getState()).isEqualTo(new Strike());
    }

    @DisplayName("firstBowl 상태 확인")
    @Test
    void firstBowlState() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(1);
        Assertions.assertThat(frame.getState()).isEqualTo(new FirstBowl(1));
    }

    @DisplayName("firstBowl 상태 확인")
    @Test
    void firstBowlState2() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(9);
        Assertions.assertThat(frame.getState()).isEqualTo(new FirstBowl(9));
    }

    @DisplayName("firstBowl 상태 확인")
    @Test
    void spairState() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(9);
        Assertions.assertThat(frame.getState()).isEqualTo(new FirstBowl(9));
    }
}