package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {

    @Test
    @DisplayName("첫 번째 투구가 스트라이크인 경우 두 번째 투구는 없다.")
    void canKnowSecondExist() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.play(new PinNumber(10));

        Assertions.assertThat(normalFrame.hasSecond()).isEqualTo(false);
    }
}
