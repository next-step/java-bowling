package bowling.domain.bowling;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {30, 11, 20})
    @DisplayName("핀은 0~10번의 값이 들어오지 않으면 Exception이 발생해야 한다.")
    void pinSaveFailTest(int input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Pin.of(input))
            .withMessageMatching("핀은 0~10 사이의 값만 저장할 수 있다.");
    }

    @Test
    @DisplayName("두번째 핀 정보가 입력될 때 남은 핀보다 많이 들어오면 Exception이 발생해야 한다.")
    void pinSaveFailBySecondPinTest() {

        // given
        Pin input = Pin.of(8);
        int second = 3;

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Pin.from(input, second))
            .withMessageMatching("첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.");
    }

}