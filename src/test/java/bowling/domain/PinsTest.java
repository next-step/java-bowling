package bowling.domain;

import bowling.exception.InputOverHitCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PinsTest {

    @DisplayName("Pins 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Pins pins = Pins.init();

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName("Pins 인스턴스가 몇 개 맞았을 때, 남는 핀을 반환하는 테스트")
    @Test
    void 반환_남는_핀() {
        // given
        int hitCount = 5;

        // when
        Pins pins = Pins.init();
        Pins remain = pins.hit(hitCount);

        // then
        assertThat(remain).isNotNull();
    }

    @DisplayName("Pins 인스턴스가 가진 갯수가 동일한 경우 같은 인스턴스로 비교하는지 테스트")
    @Test
    void 비교_핀_갯수_기준() {
        // given
        int hitCount = 5;

        // when
        Pins pins = Pins.init();
        Pins expected = pins.hit(hitCount);
        Pins actual = pins.hit(hitCount);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Pins 인스턴스가 가진 갯수를 넘는 값이 들어왔을 경우 예외처리 여부 테스트")
    @Test
    void 검증_오버된_수() {
        // given
        int overCount = 11;
        int fistHitCount = 5;
        int secondHitCount = 6;

        // when
        Pins pins = Pins.init();

        // then
        assertThatThrownBy(() -> assertThat(pins.hit(overCount)))
                .isInstanceOf(InputOverHitCountException.class)
                .hasMessage("맞은 갯수 (11) 은, 현재 남아있는 갯수 (10) 보다 큽니다.");

        assertThatThrownBy(() -> assertThat(pins.hit(fistHitCount).hit(secondHitCount)))
                .isInstanceOf(InputOverHitCountException.class)
                .hasMessage("맞은 갯수 (6) 은, 현재 남아있는 갯수 (5) 보다 큽니다.");
    }

    @DisplayName("Pins 인스턴스가 가진 갯수가 0인지 확인하는 테스트")
    @Test
    void 반환_클리어() {
        // given
        int allHitCount = 10;
        int noHitCount = 0;

        // when
        Pins pins = Pins.init();

        // then
        assertAll(
                () -> assertThat(pins.hit(allHitCount).isClear()).isTrue(),
                () -> assertThat(pins.hit(noHitCount).isClear()).isFalse()
        );
    }



}