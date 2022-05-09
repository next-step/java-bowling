package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

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
        assertThatIllegalArgumentException().isThrownBy(() -> BonusHit.of(Strike.instance(), (Pins) null));
        assertThatIllegalArgumentException().isThrownBy(() -> BonusHit.of(null, Pins.MAX));
        assertThatIllegalArgumentException().isThrownBy(() -> BonusHit.of(Strike.instance(), Collections.emptyList()));
    }

    @Test
    @DisplayName("주어진 이전 상태 그대로 반환")
    void fromState() {
        assertThat(BonusHit.of(Strike.instance(), Pins.MAX).fromState()).isEqualTo(Strike.instance());
    }

    @Test
    @DisplayName("주어진 갯수 그대로 반환")
    void counts() {
        assertThat(BonusHit.of(Strike.instance(), Pins.MAX).counts()).isEqualTo(Collections.singletonList(Pins.MAX));
    }
}
