package bowling.model.state;

import bowling.exception.ImpossiblePitchException;
import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    private final State spare = Spare.create(Pins.create(5));

    @Test
    @DisplayName(" 생성을 확인한다")
    void create() {
        assertThat(spare).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("프레임을 종료한 상태이다")
    void isFrameEnd() {
        assertThat(spare.isFrameEnd()).isTrue();
    }

    @Test
    @DisplayName("심볼은 첫번째 투구와 (/) 심볼을 반환한다")
    void getSymbol() {
        assertThat(spare.getSymbol()).isEqualTo("5|/");
    }

    @Test
    @DisplayName("투구할 수 없다")
    void doNotPitch() {
        assertThatThrownBy(() -> spare.pitch(Pins.create(3))).isInstanceOf(ImpossiblePitchException.class);
    }
}