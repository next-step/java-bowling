package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalPinNumbersTest {

    @Test
    void isFull_Miss() {
        FinalPinNumbers pinNumbers = new FinalPinNumbers(1);

        assertThat(pinNumbers.isFull()).isFalse();
        pinNumbers.addPin(8);
        assertThat(pinNumbers.isFull()).isTrue();
    }

    @DisplayName("FinalPinNumbers는 스페어/스트라이크 일 때 세 번 투구해야 isFull true")
    @Test
    void isFull_Spare() {
        FinalPinNumbers pinNumbers = new FinalPinNumbers(1);

        assertThat(pinNumbers.isFull()).isFalse();
        pinNumbers.addPin(9);
        assertThat(pinNumbers.isFull()).isFalse();
        pinNumbers.addPin(9);
        assertThat(pinNumbers.isFull()).isTrue();
    }

    @DisplayName("첫 번째 투구가 스트라이크가 아니면 첫 투구 + 두 번째 투구는 최대 10")
    @Test
    void addPin_WhenInvalidSecondPin_ThrowsIllegalStateException() {
        FinalPinNumbers pinNumbers = new FinalPinNumbers(7);

        assertThatThrownBy(() -> pinNumbers.addPin(4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("첫 번째 투구가 스트라이크면 두 번째 투구 아무 숫자나 가능")
    @Test
    void addPin_WhenFirstPinIsMaxNo_CanAddAnyPin() {
        FinalPinNumbers pinNumbers = new FinalPinNumbers(10);

        assertThatNoException().isThrownBy(() -> pinNumbers.addPin(10));
    }

    @Test
    void bonuses() {
        FinalPinNumbers pinNumbers = new FinalPinNumbers(4);
        pinNumbers.addPin(6);
        pinNumbers.addPin(5);

        assertThat(pinNumbers.spareBonus()).isEqualTo(4);
        assertThat(pinNumbers.strikeBonus()).isEqualTo(10);
        assertThat(pinNumbers.getOwnSpareBonus()).isEqualTo(5);
        assertThat(pinNumbers.getOwnStrikeBonus()).isEqualTo(11);
    }
}
