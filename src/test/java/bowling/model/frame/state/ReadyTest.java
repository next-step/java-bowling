package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.model.DownPin.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private State ready = Ready.getInstance();

    @DisplayName("싱글톤 확인")
    @Test
    void check_singleton() {
        assertThat(Ready.getInstance()).isEqualTo(Ready.getInstance());
    }

    @DisplayName("아무런 상태가 아닐 시 현 프레임을 계속 진행한다")
    @Test
    void isFinished_false() {
        // given
        State none = Ready.getInstance();

        // when && than
        assertThat(none.isFinished()).isFalse();
    }

    @DisplayName("핀이 " + MIN + "일 시 거터를 생성한다")
    @Test
    void of_whenPinsZero_thanGutter() {
        // then
        assertThat(ready.bowl(DOWN_ZERO)).isInstanceOf(Gutter.class);
    }

    @DisplayName("모두 쓰러트렸을 시 스트라이크를 반환한다")
    @Test
    void of_whenPinsAll_thanStrike() {
        assertThat(ready.bowl(DOWN_ALL)).isInstanceOf(Strike.class);
    }

    @DisplayName("일부 쓰러트렸을 시 히트를 반환한다")
    @Test
    void bowl_pinsMax_thanStrike() {
        // given
        DownPin downPinLessThenMax = DownPin.valueOf(MAX - 1);

        // then
        assertThat(ready.bowl(downPinLessThenMax)).isInstanceOf(Hit.class);
    }

    @DisplayName("쓰러트린 핀의 개수가 " + MAX + "미만일 시 현 프레임을 계속 진행할 수 있다")
    @ParameterizedTest
    @CsvSource({
            MIN + ", false",
            MAX - 1 + ", false",
            MAX + ", true",
    })
    void isFinished_false(int downPins, boolean expectedResult) {
        // given
        DownPin firstDownPin = DownPin.valueOf(downPins);

        // when
        State strike = ready.bowl(firstDownPin);

        // than
        assertThat(strike.isFinished()).isEqualTo(expectedResult);
    }
}