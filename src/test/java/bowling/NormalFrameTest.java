package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {

    @Test
    @DisplayName("첫 번째 투구와 투 번째 투구의 합이 10을 초과하면 예외처리가 발생한다.")
    void throwExceptionWhenTotalOver10() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.first(new PinNumber(7));

        Assertions.assertThatThrownBy(() -> {
            normalFrame.second(new PinNumber(4));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
