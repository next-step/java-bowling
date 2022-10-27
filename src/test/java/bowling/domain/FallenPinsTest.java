package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FallenPinsTest {

    @DisplayName("정해진 범위 외에 핀들을 생성하면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {11, -1})
    void createFail_whenPinsOutOfRange(int fallenPins) {
        assertThatIllegalArgumentException().isThrownBy(() -> FallenPins.of(fallenPins));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,true",
            "9,false"
    })
    void isMax(int fallenPins, boolean expected) {
        assertThat(FallenPins.of(fallenPins).isMax()).isEqualTo(expected);
    }
}
