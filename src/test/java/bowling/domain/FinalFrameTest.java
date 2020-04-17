package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("보너스 볼이 없을 경우 횟수를 초과할 경우 exception을 발생시킨다")
    @Test
    void noBonusBowl() {
        FinalFrame finalFrame = new FinalFrame(false);
        finalFrame.bowl(2);
        finalFrame.bowl(3);
        assertThatThrownBy(() -> {
            finalFrame.bowl(5);
        }).isInstanceOf(UnsupportedOperationException.class);

    }
}
