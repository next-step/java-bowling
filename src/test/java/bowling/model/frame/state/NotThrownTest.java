package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import static org.assertj.core.api.Assertions.*;

@DisplayName("안던진 상태")
class NotThrownTest {

    @Test
    @DisplayName("생성")
    void instance() {
        assertThatNoException().isThrownBy(NotThrown::instance);
    }

    @Test
    @DisplayName("직접 생성 불가")
    void instance_newInstance_thrownAssertionError() {
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> ReflectionUtils.newInstance(NotThrown.class));
    }

    @Test
    @DisplayName("종료 여부는 항상 거짓")
    void isEnd() {
        assertThat(NotThrown.instance().isEnd()).isFalse();
    }

    @Test
    @DisplayName("남은 핀들이 없으면 Strike, 남으면 FirstThrown")
    void state() {
        //given
        NotThrown notThrown = NotThrown.instance();
        //when, then
        assertThat(notThrown.state(Pins.MAX)).isEqualTo(Strike.instance());
        assertThat(notThrown.state(Pins.ZERO)).isEqualTo(FirstThrown.from(Pins.ZERO));
    }
}
