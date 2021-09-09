package bowling.domain.Frame;

import bowling.domain.frame.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    @Test
    void create() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isEqualTo(finalFrame);
    }

    @DisplayName("spare 인 경우 3번째 투구를 할수있다.")
    @Test
    void frameNo_spare() {
        FinalFrame finalFrame = new FinalFrame().next(1).next(9);
        assertThat(finalFrame.getFrameNo()).isEqualTo(10);
    }

    @DisplayName("strike 인 경우 3번째 투구를 할수있다.")
    @Test
    void frameNo_strike() {
        FinalFrame finalFrame = new FinalFrame().next(10).next(1);
        assertThat(finalFrame.getFrameNo()).isEqualTo(10);
    }

    @DisplayName("miss 인 경우 3번째 투구를 할수없다.")
    @Test
    void frameNo_miss() {
        FinalFrame finalFrame = new FinalFrame().next(1).next(1);
        assertThat(finalFrame.getFrameNo()).isEqualTo(11);
    }
}
