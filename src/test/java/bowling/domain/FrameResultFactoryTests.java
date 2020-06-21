package bowling.domain;

import bowling.domain.FrameResult.FrameResult;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameResultFactoryTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수에 따라 알맞는 FrameResult를 반환한다.")
    @ParameterizedTest
    @MethodSource("factoryArguments")
    void createTest(int numberOfHitPin, boolean completeResult, boolean strikeResult) {
        FrameResult frameResult = FrameResultFactory.create(numberOfHitPin);

        assertThat(frameResult.isCompleted()).isEqualTo(completeResult);
        assertThat(frameResult.isCompleted()).isEqualTo(strikeResult);
    }
    public static Stream<Arguments> factoryArguments() {
        return Stream.of(
                Arguments.of(10, true, true),
                Arguments.of(5, false, false),
                Arguments.of(0, false, false)
        );
    }

    @DisplayName("0 ~ 10을 벗어난 값으로 생성 시도 시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> FrameResultFactory.create(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("마지막 프레임의 첫번째 투구로 맞춘 핀의 수를 입력받아 객체를 생성할 수 있다.")
    @Test
    void createFinalTest() {
        int numberOfHitPin = 6;

        FrameResult frameResult = FrameResultFactory.createFinal(numberOfHitPin);

        assertThat(frameResult.isFinalFrame()).isTrue();
    }
}
