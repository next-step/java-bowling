package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ThrowResultTests {
    @DisplayName("볼링공으로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    public void createTest() {
        int numberOfHitPin = 3;
        ThrowResult throwResult = new ThrowResult(numberOfHitPin);
        assertThat(throwResult).isEqualTo(new ThrowResult(numberOfHitPin));
    }

    @DisplayName("맞춘 핀의 수는 0 ~ 10 범위를 벗어날 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    public void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> new ThrowResult(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("두 개의 객체를 더할 수 있다.")
    @Test
    public void plusTest() {
        ThrowResult firstThrowResult = new ThrowResult(3);

        assertThat(firstThrowResult.plus(new ThrowResult(5))).isEqualTo(8);
    }
}
