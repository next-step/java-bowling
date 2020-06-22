package bowling.domain;

import bowling.domain.exceptions.ExceedLimitOfNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NumberOfHitPinTests {
    @DisplayName("맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int hitPin = 10;
        NumberOfHitPin numberOfHitPin = new NumberOfHitPin(hitPin);
        assertThat(numberOfHitPin).isEqualTo(new NumberOfHitPin(hitPin));
    }
    
    @DisplayName("0 ~ 10을 벗어나는 값으로 객체를 생성할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> new NumberOfHitPin(invalidValue))
                .isInstanceOf(ExceedLimitOfNumberOfHitPinException.class);
    }
}
