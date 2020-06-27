package bowling.domain;

import bowling.domain.exceptions.ExceedLimitOfNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NumberOfHitPinTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NumberOfHitPin numberOfHitPin = new NumberOfHitPin(TEN);
        assertThat(numberOfHitPin).isEqualTo(new NumberOfHitPin(TEN));
    }

    @DisplayName("0 ~ 10을 벗어나는 값으로 객체를 생성할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> new NumberOfHitPin(invalidValue))
                .isInstanceOf(ExceedLimitOfNumberOfHitPinException.class);
    }

    @DisplayName("같은 타입의 객체끼리 더할 수 있다.")
    @Test
    void plusTest() {
        NumberOfHitPin fiveHitPin = new NumberOfHitPin(FIVE);
        assertThat(fiveHitPin.plus(new NumberOfHitPin(FIVE))).isEqualTo(new NumberOfHitPin(TEN));
    }

    @DisplayName("더해서 10 이상의 값을 지닌 객체를 만들 수 없다.")
    @Test
    void plusValidationTest() {
        NumberOfHitPin tenHitPin = new NumberOfHitPin(TEN);
        assertThatThrownBy(() -> tenHitPin.plus(new NumberOfHitPin(FIVE)))
                .isInstanceOf(ExceedLimitOfNumberOfHitPinException.class);
    }

    @DisplayName("같은 객체끼리 대소 비교를 할 수 있다.")
    @Test
    void compareTest() {
        NumberOfHitPin tenHitPin = new NumberOfHitPin(TEN);
        NumberOfHitPin fiveHitPin = new NumberOfHitPin(FIVE);

        assertThat(tenHitPin.compareTo(fiveHitPin) > 0).isTrue();
    }
}
