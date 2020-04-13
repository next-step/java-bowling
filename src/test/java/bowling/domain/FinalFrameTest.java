package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @DisplayName("보너스로 투구 할 수 없는데 시도 할 경우 예외를 발생시킨다.")
    @Test
    void exceptionBonusBowl() {
        FinalFrame finalFrame = new FinalFrame(10);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            finalFrame.bonusBowl(5);
        });
    }

    @DisplayName("보너스로 투구 할 수 있는 경우 확인한다.")
    @Test
    void bonusBowl() {
        FinalFrame finalFrame = new FinalFrame(10, true);
        finalFrame.bowl(2);
        finalFrame.bowl(8);
        finalFrame.bonusBowl(5);
        Pin bonusPin = finalFrame.getBonusPin();
        assertThat(bonusPin).isEqualTo(new Pin(5));
    }
}
