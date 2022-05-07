package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("스페어")
class SpareTest {

    @Test
    @DisplayName("첫번째 0, 두번째 10으로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> Spare.of(Pins.ZERO, Pins.MAX));
    }

    @Test
    @DisplayName("첫번재, 두번째 핀들은 필수")
    void instance_nullArgument_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.of(Pins.ZERO, null));
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.of(null, Pins.MAX));
    }

    @Test
    @DisplayName("첫번재 핀들은 반드시 10 미만")
    void instance_firstPinsMax_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.of(Pins.MAX, Pins.ZERO));
    }

    @Test
    @DisplayName("첫번재, 두번째 핀들을 합치면 반드시 10")
    void instance_sumPinsLessThanMax_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.of(Pins.ZERO, Pins.ZERO));
    }

    @Test
    @DisplayName("종료 여부는 항상 참")
    void isEnd() {
        assertThat(Spare.of(Pins.ZERO, Pins.MAX).isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 상태는 생성 불가")
    void state_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> Spare.of(Pins.ZERO, Pins.MAX).state(Pins.ZERO));
    }

    @Test
    @DisplayName("보너스 투구는 1")
    void bonusCount() {
        assertThat(Spare.of(Pins.ZERO, Pins.MAX).bonusCount()).isEqualTo(1);
    }
}
