package bowling.domain.state;

import bowling.exception.InvalidPinsSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinsTest {

    @DisplayName("Pins 인스턴스 생성 테스트")
    @Test
    void 생성() {
        // given
        int hitCount = 10;

        // when
        Pins pins = Pins.valueOf(hitCount);

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName("Pins 인스턴스에 범위를 벗어난 값 주입시 예외처리 테스트")
    @Test
    void 검증() {
        // given
        int negativeCount = -1;
        int overCount = 11;

        // when and then
        assertThatThrownBy(() -> Pins.valueOf(negativeCount))
                .isInstanceOf(InvalidPinsSizeException.class)
                .hasMessage("Pins 의 범위를 벗어난 값이 입력되었습니다.");

        // when and then
        assertThatThrownBy(() -> Pins.valueOf(overCount))
                .isInstanceOf(InvalidPinsSizeException.class)
                .hasMessage("Pins 의 범위를 벗어난 값이 입력되었습니다.");
    }

    @DisplayName("Pins 인스턴스가 소유한 값을 반환하는지 테스트")
    @Test
    void 반환() {
        // given
        int expected = 10;

        // when
        Pins pins = Pins.valueOf(expected);
        int actual = pins.count();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Pins 인스턴스가 프레임 시작시마다 초기 셋팅된 값을 반환하는지 테스트")
    @Test
    void 생성_초기값() {
        // given
        int expected = 10;

        // when
        Pins pins = Pins.full();
        int actual = pins.count();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Pins 인스턴스에 프레임이 떨어진 핀의 갯수 입력시 남은 핀이 알맞게 존재하는지 테스트")
    @Test
    void 반환_남은_핀() {
        // given
        int fallCount = 10;

        // when
        Pins pins = Pins.full();

        // then
        assertAll(
                () -> assertThat(pins.hit(fallCount)).isNotNull(),
                () -> assertThat(pins.hit(fallCount)).isInstanceOf(Pins.class),
                () -> assertThat(pins.hit(fallCount).count()).isEqualTo(0)
        );

    }

    @DisplayName("Pins 인스턴스에 남은 핀보다 큰 핀의  갯수 입력시 예외처리 테스트")
    @Test
    void 검증_남은_핀보다_큰핀_입력() {
        // given
        int fallCount = 10;

        // when
        Pins pins = Pins.full();
        Pins remain = pins.hit(fallCount);

        // then
        assertThatThrownBy(() -> remain.hit(fallCount))
                .isInstanceOf(InvalidPinsSizeException.class)
                .hasMessage("Pins 의 범위를 벗어난 값이 입력되었습니다.");
    }

    @DisplayName("Pins 인스턴스에 핀이 남았는지 테스트")
    @Test
    void 반환_isEmpty() {
        // given
        int fallCount = 10;

        // when
        Pins pins = Pins.full();
        Pins remain = pins.hit(fallCount);

        // then
        assertAll(
                () -> assertThat(pins.isEmpty()).isFalse(),
                () -> assertThat(remain.isEmpty()).isTrue()
        );
    }

    @DisplayName("Pins 인스턴스가 맞은 값이 Strike인지 테스트")
    @Test
    void 반환_isStrike() {
        // given
        Pins strike = Pins.full();
        Pins spare = Pins.valueOf(9);

        // when
        boolean firstActual = strike.isStrike();
        boolean secondActual = spare.isStrike();

        // then
        assertAll(
                () -> assertThat(firstActual).isTrue(),
                () -> assertThat(secondActual).isFalse()
        );
    }


    @DisplayName("Pins 인스턴스가 맞은 값이 스페어인지 테스트")
    @Test
    void 반환_isSpare() {
        // given
        int firstCount = 10;
        int secondCount = 0;

        // when
        Pins firstBowl = Pins.valueOf(0);
        boolean firstActual = firstBowl.isSpare(firstCount);
        boolean secondActual = firstBowl.isSpare(secondCount);

        // then
        assertAll(
                () -> assertThat(firstActual).isTrue(),
                () -> assertThat(secondActual).isFalse()
        );

    }

    @DisplayName("Pins 인스턴스가 맞은 값이 Miss인지 테스트")
    @Test
    void 반환_isMiss() {
        // given
        int firstCount = 9;
        int secondCount = 10;

        // when
        Pins firstBowl = Pins.valueOf(0);
        boolean firstActual = firstBowl.isMiss(firstCount);
        boolean secondActual = firstBowl.isMiss(secondCount);

        // then
        assertAll(
                () -> assertThat(firstActual).isTrue(),
                () -> assertThat(secondActual).isFalse()
        );
    }

}