package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("strike 확인")
    @Test
    void delivery_strike() {
        Frame frame = new Frame();
        frame.delivery(10);
        assertThat(frame.getFirstDelivery()).isEqualTo("X");
    }

    @DisplayName("spare 확인")
    @Test
    void delivery_spare() {
        Frame frame = new Frame();
        frame.delivery(3);
        assertThat(frame.getFirstDelivery()).isEqualTo("3");

        frame.delivery(7);
        assertThat(frame.getSecondDelivery()).isEqualTo("/");
    }

    @DisplayName("추가 가능성 확인")
    @Test
    void additionallyDeliverable() {
        Frame frame = new Frame();
        frame.delivery(3);
        assertThat(frame.additionallyDeliverable()).isTrue();

        frame.delivery(4);
        assertThat(frame.additionallyDeliverable()).isFalse();
    }
}
