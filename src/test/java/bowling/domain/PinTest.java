package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("핀 테스트")
class PinTest {

    @DisplayName("0 미만 또는 10 초과의 수가 입력되면 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {-1, 11})
    void pin(int input) {
        assertThatThrownBy(() -> Pin.of(input));
    }

    @ParameterizedTest(name = "{0}개 핀과 {1}개 핀을 더한 결과는 {2}개 핀이다.")
    @CsvSource({
            "0,1,1",
            "4,5,9",
            "0,10,10",
            "5,5,10"
    })
    void add(int input1, int input2, int expected) {
        Pin result = Pin.of(input1).add(Pin.of(input2));

        assertThat(result).isEqualTo(Pin.of(expected));
    }
}
