package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NullPinsTest {

    @Test
    @DisplayName("NullPins 생성 테스트")
    void create() {
        // given
        final NullPins nullPins = new NullPins();

        // when
        // then
        assertThat(nullPins.frameStatus()).isEqualTo(FrameStatus.NONE);
    }
}
