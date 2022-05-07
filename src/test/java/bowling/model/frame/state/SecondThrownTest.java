package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("두번째 던진 상태")
class SecondThrownTest {

    @Test
    @DisplayName("첫번째, 두번째 0 핀들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> SecondThrown.of(Pins.ZERO, Pins.ZERO));
    }

    @Test
    @DisplayName("첫번재, 두번째 핀들은 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> SecondThrown.of(Pins.ZERO, null));
        assertThatIllegalArgumentException().isThrownBy(() -> SecondThrown.of(null, Pins.ZERO));
    }

    @Test
    @DisplayName("첫번재, 두번째 핀들을 합치면 반드시 10 미만")
    void instance_sumPinsMax_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> SecondThrown.of(Pins.ZERO, Pins.MAX));
    }

    @Test
    @DisplayName("종료 여부는 항상 참")
    void isEnd() {
        assertThat(SecondThrown.of(Pins.ZERO, Pins.ZERO).isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 상태는 생성 불가")
    void state_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> SecondThrown.of(Pins.ZERO, Pins.ZERO).state(Pins.ZERO));
    }
}
