package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InputOverHitCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PinCountTest {

    @DisplayName("PinCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        PinCount pinCount = PinCount.initialize();

        // then
        assertThat(pinCount).isNotNull();
    }

    @DisplayName("PinCount 인스턴스가 맞은 갯수만큼 감소하는지에 대한 테스트")
    @Test
    void 감소() {
        // when
        PinCount pinCount = PinCount.initialize();

        // then
        assertAll(
                () -> assertThat(pinCount.hit(PinCount.valueOf(10))).isNotNull(),
                () -> assertThat(pinCount.hit(PinCount.valueOf(0))).isNotNull()
        );

    }

    @DisplayName("PinCount 인스턴스에 범위를 벗어난 수 입력시 예외처리 여부 테스트")
    @Test
    void 검증_범위를_벗어난_수() {
        // when and then
        assertThatThrownBy(() -> PinCount.valueOf(-1))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( -1 ) 는 사용할 수 없는 갯수 입니다.");

        // when and then
        assertThatThrownBy(() -> PinCount.valueOf(11))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( 11 ) 는 사용할 수 없는 갯수 입니다.");

    }

    @DisplayName("PinCount 인스턴스가 소유한 값보다 큰수가 들어왔을 경우 예외처리 여부 테스트")
    @Test
    void 검증_초과된_수() {
        // when
        PinCount pinCount = PinCount.initialize();

        // then
        assertThatThrownBy(() -> pinCount.hit(PinCount.valueOf(1)).hit(PinCount.valueOf(10)))
                .isInstanceOf(InputOverHitCountException.class)
                .hasMessage("남은 수 ( 9 ) 에 비해, 맞은 횟수 ( 10 )는 더 큰 값입니다.");
    }

    @DisplayName("PinCount 인스턴스에 핀이 남아있는지 여부 테스트")
    @Test
    void 반환_남은_핀_존재여부() {
        // when
        PinCount firstCount = PinCount.initialize();
        PinCount secondCount = PinCount.empty();

        // then
        assertAll(
                () -> assertThat(firstCount.isEmpty()).isFalse(),
                () -> assertThat(secondCount.isEmpty()).isTrue()
        );
    }

    @DisplayName("PinCount 인스턴스가 빈 값을 가진 인스턴스를 반환하는지 테스트")
    @Test
    void 반환_빈값() {
        // when
        PinCount pinCount = PinCount.empty();

        // then
        assertThat(pinCount.count()).isEqualTo(0);
    }

    @DisplayName("PinCount 인스턴스가 Spare 여부를 반환하는지 테스트")
    @Test
    void 반환_Spare_여부() {
        // when
        PinCount pinCount = PinCount.empty();
        boolean firstActual = pinCount.isSpare(PinCount.valueOf(10));
        boolean secondActual = pinCount.isSpare(PinCount.valueOf(5));

        // then
        assertAll(
                () -> assertThat(firstActual).isTrue(),
                () -> assertThat(secondActual).isFalse()
        );
    }

    @DisplayName("PinCount 인스턴스가 Miss 여부를 반환하는지 테스트")
    @Test
    void 반환_Miss_여부() {
        // when
        PinCount pinCount = PinCount.empty();
        boolean firstActual = pinCount.isMiss(PinCount.valueOf(9));
        boolean secondActual = pinCount.isMiss(PinCount.valueOf(10));

        // then
        assertAll(
                () -> assertThat(firstActual).isTrue(),
                () -> assertThat(secondActual).isFalse()
        );
    }

}