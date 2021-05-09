package bowling.domain.state;

import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.PinsNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        State miss = Miss.of(firstPins, secondPins);

        assertAll(
                () -> assertThat(miss).isNotNull(),
                () -> assertThat(miss).isInstanceOf(Miss.class)
        );
    }

    @DisplayName("Miss 인스턴스 생성시 null 입력 시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins firstPins = null;
        Pins secondPins = null;

        // when and then
        assertThatThrownBy(() -> Miss.of(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Miss 인스턴스 생성시 입력되는 값의 합이 10 이상일시 예외처리 테스트")
    @Test
    void 검증_값의_합이_10미만() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);

        // when and then
        assertThatThrownBy(() -> Miss.of(firstPins, secondPins))
                .isInstanceOf(InvalidMissSizeException.class)
                .hasMessage("Miss 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }
}