package bowling.domain;

import bowling.domain.exceptions.CannotBowlException;
import bowling.domain.exceptions.CannotDoNextFrameException;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.frameResult.FrameResult;
import bowling.domain.frameResult.NormalFrameResult;
import bowling.domain.frameResult.StrikeFrameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTests {
    private static final int STRIKE_HIT_PIN_NUMBER = 10;

    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTEst() {
        int numberOfHitPin = 5;
        FrameResult expectResult = FrameResultFactory.create(numberOfHitPin);

        Assertions.assertThat(NormalFrame.bowlFirst(numberOfHitPin)).isEqualTo(new NormalFrame(expectResult, null));
    }

    @DisplayName("잘못된 값의 맞춘 핀의 수를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> NormalFrame.bowlFirst(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("FrameResult가 마무리되지 않은 경우 두번째 투구를 진행할 수 있다.")
    @Test
    void bowlSecondTest() {
        int firstNumberOfHitPin = 5;
        int secondNumberOfHitPin = 3;

        NormalFrameResult expectFirstResult = (NormalFrameResult) FrameResultFactory.create(firstNumberOfHitPin);
        NormalFrameResult expectedSecondResult = expectFirstResult.secondBowl(secondNumberOfHitPin);

        assertThat(new NormalFrame(expectFirstResult, null).bowlSecond(secondNumberOfHitPin))
                .isEqualTo(new NormalFrame(expectedSecondResult, null));
    }

    @DisplayName("스트라이크 처리된 프레임에 두번째 투구를 진행할 수 없다.")
    @Test
    void bowlSecondToStrikeTest() {
        int secondPin = 2;

        NormalFrame normalFrame = NormalFrame.bowlFirst(STRIKE_HIT_PIN_NUMBER);

        assertThatThrownBy(() -> normalFrame.bowlSecond(secondPin))
                .isInstanceOf(CannotBowlException.class);
    }

    @DisplayName("스트라이크가 아닌 프레임에 세번째 투구를 진행할 수 없다.")
    @Test
    void bowlSecondValidationTest() {
        int numberOfHitPin = 2;

        NormalFrame normalFrame = NormalFrame.bowlFirst(numberOfHitPin).bowlSecond(numberOfHitPin);

        assertThatThrownBy(() -> normalFrame.bowlSecond(numberOfHitPin))
                .isInstanceOf(CannotBowlException.class);
    }

    @DisplayName("현재 프레임에서 다음 프레임을 생성할 수 있다.")
    @Test
    void createNextFrameTest() {
        int numberOfHitPin = 3;

        NormalFrame normalFrame = NormalFrame.bowlFirst(STRIKE_HIT_PIN_NUMBER);
        NormalFrame nextNormalFrame = normalFrame.next(numberOfHitPin);

        assertThat(nextNormalFrame).isEqualTo(NormalFrame.bowlFirst(numberOfHitPin));
        assertThat(normalFrame).isEqualTo(new NormalFrame(new StrikeFrameResult(), nextNormalFrame));
    }

    @DisplayName("현재 프레임 결과가 마무리되지 않으면 다음 프레임을 생성할 수 없다.")
    @Test
    void createNextFrameValidationTest() {
        int numberOfHitPin = 3;

        NormalFrame normalFrame = NormalFrame.bowlFirst(numberOfHitPin);

        assertThatThrownBy(() -> normalFrame.next(numberOfHitPin))
                .isInstanceOf(CannotDoNextFrameException.class);
    }

    @DisplayName("마지막 프레임인지 알려줄 수 있다.")
    @Test
    void isFinalFrameTest() {
        int numberOfHitPin = 5;
        NormalFrame normalFrame = NormalFrame.bowlFirst(numberOfHitPin);

        assertThat(normalFrame.isFinal()).isFalse();
    }

    @DisplayName("프레임의 현재 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("statusResource")
    void calculateStatusTest(int numberOfHitPin, FrameStatuses expectedResult) {
        NormalFrame normalFrame = NormalFrame.bowlFirst(numberOfHitPin);

        assertThat(normalFrame.calculateCurrentStatus()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> statusResource() {
        return Stream.of(
                Arguments.of(0, new FrameStatuses(Collections.singletonList(FrameStatus.GUTTER))),
                Arguments.of(5, new FrameStatuses(Collections.singletonList(FrameStatus.FIVE))),
                Arguments.of(10, new FrameStatuses(Collections.singletonList(FrameStatus.STRIKE)))
        );
    }
}
