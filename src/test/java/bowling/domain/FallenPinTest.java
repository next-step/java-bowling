package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FallenPinTest {

    @DisplayName("정해진 범위 외에 핀들을 생성하면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {11, -1})
    void createFail_whenPinsOutOfRange(int fallenPinCount) {
        assertThatIllegalArgumentException().isThrownBy(() -> FallenPin.of(fallenPinCount));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,true",
            "9,false"
    })
    void isMax(int fallenPinCount, boolean expected) {
        assertThat(FallenPin.of(fallenPinCount).isMax()).isEqualTo(expected);
    }
}
