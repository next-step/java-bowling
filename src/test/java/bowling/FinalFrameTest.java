package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.PinNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {

    @Test
    @DisplayName("첫 번째 투구가 스트라이크인 경우 세 번째 투구 기회가 있다.")
    void canKnowThirdExistByStrike() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(new PinNumber(10));
        finalFrame.play(new PinNumber(3));

        Assertions.assertThat(finalFrame.hasThird()).isEqualTo(true);
    }

    @Test
    @DisplayName("두 번째 까지의 투구가 스페어인 경우 세 번째 투구 기회가 있다.")
    void canKnowThirdExistBySpare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.play(new PinNumber(7));
        finalFrame.play(new PinNumber(3));

        Assertions.assertThat(finalFrame.hasThird()).isEqualTo(true);
    }
}
