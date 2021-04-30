package bowling.domain.state;

import bowling.domain.HitCount;
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
        Pins pins = Pins.initialize();

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName("Pins 인스턴스가 맞은 갯수만큼 감소하는지에 대한 테스트")
    @Test
    void 감소() {
        // when
        Pins pins = Pins.initialize();

        // then
        assertAll(
                () -> assertThat(pins.hit(HitCount.valueOf(10))).isNotNull(),
                () -> assertThat(pins.hit(HitCount.valueOf(0))).isNotNull()
        );

    }

    @DisplayName("Pins 인스턴스가 소유한 값보다 큰수가 들어왔을 경우 예외처리 여부 테스트")
    @Test
    void 검증_초과된_수() {
        // when
        Pins pins = Pins.initialize();

        // then
        assertThatThrownBy(() -> pins.hit(HitCount.valueOf(1)).hit(HitCount.valueOf(10)))
                .isInstanceOf(InputOverHitCountException.class)
                .hasMessage("( 10 )은, 남은 수 ( 9 ) 보다 큰 값입니다.");

    }

    @DisplayName("Pins 인스턴스에 핀이 남아있는지 여부 테스트")
    @Test
    void 검증_남은_핀_존재여부() {
        // when
        Pins pins = Pins.initialize();

        // then
        assertAll(
                () -> assertThat(pins.hit(HitCount.valueOf(10)).isEmpty()).isTrue(),
                () -> assertThat(pins.hit(HitCount.valueOf(0)).isEmpty()).isFalse()
        );

    }

}