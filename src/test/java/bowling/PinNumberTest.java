package bowling;

import bowling.domain.PinNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("쓰러트린 핀의 개수가 0 ~ 10 이외의 숫자인 경우 예외처리가 발생한다.")
    void throwException(int number) {
        Assertions.assertThatThrownBy(() -> {
            new PinNumber(number);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
