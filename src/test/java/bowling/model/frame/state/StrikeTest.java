package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import static org.assertj.core.api.Assertions.*;

@DisplayName("스트라이크")
class StrikeTest {

    @Test
    @DisplayName("생성")
    void instance() {
        assertThatNoException().isThrownBy(Strike::instance);
    }

    @Test
    @DisplayName("직접 생성 불가")
    void instance_newInstance_thrownAssertionError() {
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> ReflectionUtils.newInstance(Strike.class));
    }

    @Test
    @DisplayName("종료 여부는 항상 참")
    void isEnd() {
        assertThat(Strike.instance().isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 상태는 생성 불가")
    void state_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> Strike.instance().state(Pins.ZERO));
    }

    @Test
    @DisplayName("보너스 투구는 2")
    void bonusCount() {
        assertThat(Strike.instance().bonusCount()).isEqualTo(2);
    }
}
