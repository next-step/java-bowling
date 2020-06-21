package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberOfHitPinTests {
    @DisplayName("볼링공으로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    public void createTest() {
        int numberOfHitPin = 3;
        NumberOfHitPin throwResult = new NumberOfHitPin(numberOfHitPin);
        assertThat(throwResult).isEqualTo(new NumberOfHitPin(numberOfHitPin));
    }

    @DisplayName("맞춘 핀의 수는 0 ~ 10 범위를 벗어날 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    public void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> new NumberOfHitPin(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("두 개의 객체를 더할 수 있다.")
    @Test
    public void plusTest() {
        NumberOfHitPin firstNumberOfHitPin = new NumberOfHitPin(3);

        assertThat(firstNumberOfHitPin.plus(new NumberOfHitPin(5))).isEqualTo(new NumberOfHitPin(8));
    }

    @DisplayName("대소 비교가 가능하다")
    @Test
    public void compareTest() {
        assertThat(new NumberOfHitPin(5).compareTo(new NumberOfHitPin(4)) > 0).isTrue();
    }
}
