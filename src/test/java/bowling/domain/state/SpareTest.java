package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SpareTest {

    @Test
    @DisplayName("보너스 점수 개수는 1개")
    void bonus_one() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        Spare spare = new Spare(pins);
        //then
        assertThat(spare.bonusCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("스코어가 스페어가 스페어 생성 불가")
    void is_strike() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(5));
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Spare(pins));
    }

    @Test
    @DisplayName("파이널 프레임에서 보너스 기회 있음")
    void can_get_bonus_in_final_frame() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        Spare spare = new Spare(pins);
        //then
        assertThat(spare.canGetBonus()).isTrue();
    }

    @Test
    @DisplayName("종료된 상태")
    void is_finished() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        Spare spare = new Spare(pins);
        //then
        assertThat(spare.isFinish()).isTrue();
    }

    @Test
    @DisplayName("남은 핀 개수 0")
    void remain_pins_0() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        Spare spare = new Spare(pins);
        //then
        assertThat(spare.getRemainPins()).isZero();
    }

    @Test
    @DisplayName("볼은 더 던지먼 오류")
    void bowl_illegalStateException() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        Spare spare = new Spare(pins);
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> spare.bowl(new Pin(10)));
    }
}