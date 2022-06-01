package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("보너스 점수 입력 가능 확인")
    @Test
    void delivery_possibilityBonus() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.delivery(3);
        assertThat(finalFrame.getFirstDelivery()).isEqualTo("3");

        finalFrame.delivery(7);
        assertThat(finalFrame.getSecondDelivery()).isEqualTo("/");

        finalFrame.delivery(10);
        assertThat(finalFrame.getBonusDelivery()).isEqualTo("X");
    }

    @DisplayName("보너스 점수에서도 스페어 처리 확인")
    @Test
    void spare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.delivery(10);
        assertThat(finalFrame.getFirstDelivery()).isEqualTo("X");

        finalFrame.delivery(8);
        assertThat(finalFrame.getSecondDelivery()).isEqualTo("8");

        finalFrame.delivery(2);
        assertThat(finalFrame.getBonusDelivery()).isEqualTo("/");
    }

    @DisplayName("보너스 점수 입력 가능성 확인")
    @Test
    void additionallyDeliverable() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.delivery(10);
        assertThat(finalFrame.additionallyDeliverable()).isTrue();

        finalFrame.delivery(7);
        assertThat(finalFrame.additionallyDeliverable()).isTrue();

        finalFrame.delivery(3);
        assertThat(finalFrame.additionallyDeliverable()).isFalse();
    }
}