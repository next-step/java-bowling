package bowling.domain;

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
}
