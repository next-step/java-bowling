package bowling;

import bowling.domain.NumberOfPlayer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberOfPlayerTest {
    @ParameterizedTest(name = "정수 타입 예외 테스트")
    @ValueSource(strings = {"3명", "일곱명", "가나다"})
    void NumericTypeTest(String number) {
        assertThatThrownBy(() -> new NumberOfPlayer(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 정수타입이여야 합니다.");
    }

    @ParameterizedTest(name = "정수 범위 테스트")
    @ValueSource(strings = {"-5", "-3", "0"})
    void nameLengthExceptionTest(String number) {
        assertThatThrownBy(() -> new NumberOfPlayer(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 양의 정수여야 합니다.");
    }
}
