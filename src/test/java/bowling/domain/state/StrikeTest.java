package bowling.domain.state;

import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    private final State strike = Strike.create();

    @Test
    @DisplayName("생성을 확인한다")
    void create() {
        assertThat(strike).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("심볼을 확인한다")
    void getSymbol() {
        assertThat(strike.getSymbol()).isEqualTo("X");
    }

    @Test
    @DisplayName("프레임을 종료한 상태이다")
    void isFrameStateEnd() {
        assertThat(strike.isFrameEnd()).isTrue();
    }

    @Test
    @DisplayName("투구를 할 수 없다")
    void doNotPitch() {
        assertThatThrownBy(() -> strike.pitch(Pins.create(3))).isInstanceOf(ImpossiblePitchException.class);
    }

}
