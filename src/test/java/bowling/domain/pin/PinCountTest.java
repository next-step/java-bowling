package bowling.domain.pin;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinCountTest {

    @DisplayName("볼링 수가 0 ~ 10 이외의 숫자인 경우 예외 반환")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void validRange(final int count) {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> PinCount.of(count)
        );
    }

    @DisplayName("볼링 수의 논리적 동치성 비교 및 생성 확인")
    @Test
    public void create() {
        final int count = 0;
        assertThat(PinCount.of(count))
                .isEqualTo(PinCount.of(count));
    }

    @DisplayName("인자의 수 만큼 증가한 볼링 수를 반환")
    @ParameterizedTest
    @CsvSource({ "0,0,0", "1,2,3" })
    void increase(final int operandA, final int operandB, final int expected) {
        assertThat(PinCount.of(operandA).sum(PinCount.of(operandB)).getCount())
                .isEqualTo(expected);
    }

    @DisplayName("최대 개수와 동일한지 여부를 반환")
    @Test
    public void isMaxCount() {
        assertThat(PinCount.of(PinCount.MAX_COUNT).isMaxCount())
                .isTrue();
    }

    @DisplayName("최소 개수와 동일한지 여부를 반환")
    @Test
    public void isMinCount() {
        assertThat(PinCount.of(PinCount.MIN_COUNT).isMinCount())
                .isTrue();
    }

    @DisplayName("최대 개수보다 작은지에 대한 여부를 반환")
    @ParameterizedTest
    @CsvSource({ "10,false", "0,true", "1,true" })
    void isLessThanMaxCount(final int pinCount, final boolean expected) {
        assertThat(PinCount.of(pinCount).isLessThanMaxCount())
                .isEqualTo(expected);
    }
}
