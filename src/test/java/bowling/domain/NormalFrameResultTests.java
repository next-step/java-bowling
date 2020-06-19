package bowling.domain;

import bowling.domain.exceptions.ExceedMaxNumberOfHitPinSumException;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalFrameResultTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력해서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int numberOfHitPin = 9;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstThrow(numberOfHitPin);
        assertThat(normalFrameResult.isStrike()).isFalse();
    }

    @DisplayName("첫번째 투구로 맞춘 핀의 수는 0 ~ 9의 범위를 벗어날 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 10 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> NormalFrameResult.firstThrow(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("두번째 투구로 맞춘 핀의 수를 입력받을 수 있다.")
    @Test
    void secondNumberOfHitPinTest() {
        int numberOfHitPin = 5;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstThrow(numberOfHitPin);
        NormalFrameResult afterSecondThrow = normalFrameResult.secondThrow(numberOfHitPin);

        assertThat(afterSecondThrow.isCompleted()).isTrue();
        assertThat(afterSecondThrow).isEqualTo(new NormalFrameResult(5, 5));
    }

    @DisplayName("두번째 투구로 맞춘 핀의 수가 0 이하일 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, -2 })
    void secondNumberOfHitPinMinValidationTest(int invalidSecondHitPin) {
        int numberOfHitPin = 5;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstThrow(numberOfHitPin);
        assertThatThrownBy(() -> normalFrameResult.secondThrow(invalidSecondHitPin))
            .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("맞춘 핀 수의 총합이 10을 넘길 수 없다.")
    @Test
    void secondNumberOfHitPinValidationTest() {
        int firstNumberOfHitPin = 5;
        int secondNumberOfHitPin = 6;

        NormalFrameResult normalFrameResult = NormalFrameResult.firstThrow(firstNumberOfHitPin);
        assertThatThrownBy(() -> normalFrameResult.secondThrow(secondNumberOfHitPin))
            .isInstanceOf(ExceedMaxNumberOfHitPinSumException.class);
    }
}
