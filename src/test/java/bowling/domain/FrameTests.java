package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTEst() {
        int numberOfHitPin = 5;
        FrameResult expectResult = FrameResultFactory.create(numberOfHitPin);

        assertThat(Frame.bowlFirst(numberOfHitPin)).isEqualTo(new Frame(expectResult, null));
    }

    @DisplayName("잘못된 값의 맞춘 핀의 수를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> Frame.bowlFirst(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }
}
