package bowling.domain.state;

import bowling.exception.InvalidPinsSizeException;
import bowling.exception.PinsNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins firstPins = Pins.valueOf(0);
        Pins secondPins = Pins.valueOf(10);

        // when
        State spare = Spare.of(firstPins, secondPins);

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("Spare 인스턴스 생성시 null 입력 시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins firstPins = null;
        Pins secondPins = null;

        // when and then
        assertThatThrownBy(() -> Spare.of(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Spare 인스턴스 생성시 두 핀의 사이즈 합이 10이 아닐 경우 예외처리 테스트")
    @Test
    void 검증_사이즈() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(10);

        // when and then
        assertThatThrownBy(() -> Spare.of(firstPins, secondPins))
                .isInstanceOf(InvalidSpareSizeException.class)
                .hasMessage("Spare 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }
}