package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinNumbersTest {

    @DisplayName("두 번째 투구까지 채우면 isFull true. 스트라이크는 한 번만 투구하면 true")
    @Test
    void isFull() {
        PinNumbers nine = new PinNumbers(9);

        assertThat(nine.isFull()).isFalse();
        nine.addPin(1);
        assertThat(nine.isFull()).isTrue();
        assertThat(new PinNumbers(10).isFull()).isTrue();
    }

    @ParameterizedTest(name = "두 투구의 합이 10을 넘을 수 없다")
    @CsvSource({"8,4", "7, 5", "1, 10"})
    void addPin_WhenPinNumberSumIsOver10_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new PinNumbers(firstNo).addPin(secondNo))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("첫 번째 투구가 스트라이크면 핀을 추가할 수 없다")
    @Test
    void addPin_FirstNumberIs10_ThrowsIllegalStateException() {
        assertThatThrownBy(() -> new PinNumbers(10).addPin(1))
                .isInstanceOf(IllegalStateException.class);
    }
}
