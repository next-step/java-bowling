package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MissTest {

    @Test
    @DisplayName("보너스 점수 개수는 0개")
    void bonus_zero() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(1));
        Miss miss = new Miss(pins);
        //then
        assertThat(miss.bonusCount()).isZero();
    }

    @Test
    @DisplayName("파이널 프레임에서 보너스 기회 없음")
    void cannot_get_bonus_in_final_frame() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(1));
        Miss miss = new Miss(pins);
        //then
        assertThat(miss.canGetBonus()).isFalse();
    }

    @Test
    @DisplayName("스코어가 미스가 아니면 미스 생성 불가")
    void is_miss() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(6));
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Miss(pins));
    }

    @Test
    @DisplayName("종료된 상태")
    void is_finished() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(1));
        Miss miss = new Miss(pins);
        //then
        assertThat(miss.isFinish()).isTrue();
    }

    @Test
    @DisplayName("남은 핀 개수 계산")
    void remain_pins() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(1));
        Miss miss = new Miss(pins);
        //then
        assertThat(miss.getRemainPins()).isEqualTo(5);
    }

    @Test
    @DisplayName("볼은 더 던지먼 오류")
    void bowl_illegalStateException() {
        //given
        Pins pins = Pins.of(new Pin(4));
        pins.bowl(new Pin(1));
        Miss miss = new Miss(pins);
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> miss.bowl(new Pin(10)));
    }

}
