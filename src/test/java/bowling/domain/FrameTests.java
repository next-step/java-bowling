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

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTests {
    private static final int STRIKE_HIT_PIN_NUMBER = 10;

    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTEst() {
        int numberOfHitPin = 5;
        FrameResult expectResult = FrameResultFactory.create(numberOfHitPin);

        Assertions.assertThat(Frame.bowlFirst(numberOfHitPin)).isEqualTo(new Frame(expectResult, null));
    }

    @DisplayName("잘못된 값의 맞춘 핀의 수를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> Frame.bowlFirst(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("FrameResult가 마무리되지 않은 경우 두번째 투구를 진행할 수 있다.")
    @Test
    void bowlSecondTest() {
        int firstNumberOfHitPin = 5;
        int secondNumberOfHitPin = 3;

        NormalFrameResult expectFirstResult = (NormalFrameResult) FrameResultFactory.create(firstNumberOfHitPin);
        NormalFrameResult expectedSecondResult = expectFirstResult.secondBowl(secondNumberOfHitPin);

        assertThat(new Frame(expectFirstResult, null).bowlSecond(secondNumberOfHitPin))
                .isEqualTo(new Frame(expectedSecondResult, null));
    }

    @DisplayName("스트라이크 처리된 프레임에 두번째 투구를 진행할 수 없다.")
    @Test
    void bowlSecondToStrikeTest() {
        int secondPin = 2;

        Frame frame = Frame.bowlFirst(STRIKE_HIT_PIN_NUMBER);

        assertThatThrownBy(() -> frame.bowlSecond(secondPin))
                .isInstanceOf(CannotBowlException.class);
    }

    @DisplayName("스트라이크가 아닌 프레임에 세번째 투구를 진행할 수 없다.")
    @Test
    void bowlSecondValidationTest() {
        int numberOfHitPin = 2;

        Frame frame = Frame.bowlFirst(numberOfHitPin).bowlSecond(numberOfHitPin);

        assertThatThrownBy(() -> frame.bowlSecond(numberOfHitPin))
                .isInstanceOf(CannotBowlException.class);
    }

    @DisplayName("현재 프레임에서 다음 프레임을 생성할 수 있다.")
    @Test
    void createNextFrameTest() {
        int numberOfHitPin = 3;

        Frame frame = Frame.bowlFirst(STRIKE_HIT_PIN_NUMBER);
        Frame nextFrame = frame.next(numberOfHitPin);

        assertThat(nextFrame).isEqualTo(Frame.bowlFirst(numberOfHitPin));
        assertThat(frame).isEqualTo(new Frame(new StrikeFrameResult(), nextFrame));
    }

    @DisplayName("현재 프레임 결과가 마무리되지 않으면 다음 프레임을 생성할 수 없다.")
    @Test
    void createNextFrameValidationTest() {
        int numberOfHitPin = 3;

        Frame frame = Frame.bowlFirst(numberOfHitPin);

        assertThatThrownBy(() -> frame.next(numberOfHitPin))
                .isInstanceOf(CannotDoNextFrameException.class);
    }

    @DisplayName("마지막 프레임인지 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("isFinalFrameResource")
    void isFinalFrameTest(FrameResult frameResult, boolean expectedResult) {
        Frame frame = new Frame(frameResult, null);

        assertThat(frame.isFinal()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> isFinalFrameResource() {
        return Stream.of(
                Arguments.of(FrameResultFactory.create(5), false),
                Arguments.of(FrameResultFactory.createFinal(5), true)
        );
    }

    @DisplayName("프레임의 현재 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("statusResource")
    void calculateStatusTest(int numberOfHitPin, FrameStatus expectedResult) {
        Frame frame = Frame.bowlFirst(numberOfHitPin);

        assertThat(frame.calculateCurrentStatus()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> statusResource() {
        return Stream.of(
                Arguments.of(0, FrameStatus.GUTTER),
                Arguments.of(5, FrameStatus.FIVE),
                Arguments.of(10, FrameStatus.STRIKE)
        );
    }
}
