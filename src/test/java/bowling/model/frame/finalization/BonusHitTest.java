package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("보너스 맞춘 정보")
class BonusHitTest {

    @Test
    @DisplayName("이전 상태와 핀들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> BonusHit.of(Strike.instance(), Pins.MAX));
    }

    @Test
    @DisplayName("이전 상태와 핀들은 필수")
    void instance_nullArguments_throwIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> BonusHit.of(Strike.instance(), null));
        assertThatIllegalArgumentException().isThrownBy(() -> BonusHit.of(null, Pins.MAX));
    }
}
