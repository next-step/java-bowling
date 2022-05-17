package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.frameresult.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPinNumbersTest {

    @DisplayName("두 번째 투구까지 채우면 isFull true. 스트라이크는 한 번만 투구하면 true")
    @Test
    void isFull() {
        NormalPinNumbers nine = new NormalPinNumbers(9);

        assertThat(nine.isFull()).isFalse();
        nine.addPin(1);
        assertThat(nine.isFull()).isTrue();
        assertThat(new NormalPinNumbers(10).isFull()).isTrue();
    }

    @ParameterizedTest(name = "두 투구의 합이 10을 넘을 수 없다")
    @CsvSource({"8,4", "7, 5", "1, 10"})
    void addPin_WhenPinNumberSumIsOver10_ThrowsIllegalStateException(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new NormalPinNumbers(firstNo).addPin(secondNo))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("첫 번째 투구가 스트라이크면 핀을 추가할 수 없다")
    @Test
    void addPin_FirstNumberIs10_ThrowsIllegalStateException() {
        assertThatThrownBy(() -> new NormalPinNumbers(10).addPin(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForGetResult")
    void getResult(int firstNo, int secondNo, Class<? extends FrameResult> result) {
        NormalPinNumbers pinNumbers = new NormalPinNumbers(firstNo);

        if (firstNo != MAX_PIN_NO) {
            pinNumbers.addPin(secondNo);
        }

        assertThat(pinNumbers.getResult()).isInstanceOf(result);
    }

    private static Stream<Arguments> provideArgumentsForGetResult() {
        return Stream.of(
                Arguments.of(9, 0, Miss.class),
                Arguments.of(9, 1, Spare.class),
                Arguments.of(10, 0, Strike.class));
    }

    @Test
    void strikeBonus() {
        NormalPinNumbers pinNumbers = new NormalPinNumbers(5);
        pinNumbers.addPin(4);

        assertThat(pinNumbers.strikeBonus()).isEqualTo(9);
    }
}
