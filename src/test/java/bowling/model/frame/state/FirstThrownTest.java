package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("첫번째 던진 상태")
class FirstThrownTest {

    @Test
    @DisplayName("0개 핀들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> FirstThrown.from(Pins.ZERO));
    }

    @Test
    @DisplayName("핀들은 필수")
    void instance_nullPins_throwIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> FirstThrown.from(null));
    }

    @Test
    @DisplayName("핀들은 반드시 10개 미만")
    void instance_maxPins_throwIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> FirstThrown.from(Pins.MAX));
    }

    @Test
    @DisplayName("종료 여부는 항상 거짓")
    void isEnd() {
        assertThat(FirstThrown.from(Pins.ZERO).isEnd()).isFalse();
    }

    @Test
    @DisplayName("남은 핀들이 없으면 Spare, 남으면 SecondThrown")
    void state() {
        //given
        FirstThrown zeroThrown = FirstThrown.from(Pins.ZERO);
        //when, then
        assertThat(zeroThrown.state(Pins.MAX)).isEqualTo(Spare.from(Pins.ZERO));
        assertThat(zeroThrown.state(Pins.ZERO)).isEqualTo(SecondThrown.of(Pins.ZERO, Pins.ZERO));
    }

    @Test
    @DisplayName("주어진 핀들 그대로 반환")
    void countOfHit() {
        assertThat(FirstThrown.from(Pins.ZERO).countOfHit()).isEqualTo(Pins.ZERO);
    }

    @Test
    @DisplayName("남은 투구는 1")
    void remainCount() {
        assertThat(FirstThrown.from(Pins.ZERO).remainCount()).isEqualTo(1);
    }
}
