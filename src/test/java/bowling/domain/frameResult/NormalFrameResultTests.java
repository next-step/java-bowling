package bowling.domain.frameResult;

import bowling.domain.FrameStatus;
import bowling.domain.FrameStatuses;
import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalFrameResultTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력해서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int numberOfHitPin = 9;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstBowl(numberOfHitPin);
        assertThat(normalFrameResult.isStrikeResult()).isFalse();
    }

    @DisplayName("첫번째 투구로 맞춘 핀의 수는 0 ~ 9의 범위를 벗어날 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, 10 })
    void createValidationTest(int invalidValue) {
        assertThatThrownBy(() -> NormalFrameResult.firstBowl(invalidValue))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("두번째 투구로 맞춘 핀의 수를 입력받을 수 있다.")
    @Test
    void secondNumberOfHitPinTest() {
        int numberOfHitPin = 5;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstBowl(numberOfHitPin);
        NormalFrameResult afterSecondThrow = normalFrameResult.secondBowl(numberOfHitPin);

        assertThat(afterSecondThrow.isCompleted()).isTrue();
        assertThat(afterSecondThrow).isEqualTo(new NormalFrameResult(new NumberOfHitPin(5), new NumberOfHitPin(5)));
    }

    @DisplayName("두번째 투구로 맞춘 핀의 수가 0 이하일 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = { -1, -2 })
    void secondNumberOfHitPinMinValidationTest(int invalidSecondHitPin) {
        int numberOfHitPin = 5;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstBowl(numberOfHitPin);
        assertThatThrownBy(() -> normalFrameResult.secondBowl(invalidSecondHitPin))
            .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("맞춘 핀 수의 총합이 10을 넘길 수 없다.")
    @Test
    void secondNumberOfHitPinValidationTest() {
        int firstNumberOfHitPin = 5;
        int secondNumberOfHitPin = 6;

        NormalFrameResult normalFrameResult = NormalFrameResult.firstBowl(firstNumberOfHitPin);
        assertThatThrownBy(() -> normalFrameResult.secondBowl(secondNumberOfHitPin))
            .isInstanceOf(InvalidNumberOfHitPinException.class);
    }

    @DisplayName("첫번째 투구만 진행됐을 때 상황에 맞는 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("inProgressResource")
    void calculateCurrentStatusWhenInProgress(int numberOfHitPin, FrameStatuses expectedStatus) {
        NormalFrameResult normalFrameResult = NormalFrameResult.firstBowl(numberOfHitPin);

        assertThat(normalFrameResult.calculateCurrentStatus()).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> inProgressResource() {
        return Stream.of(
                Arguments.of(0, new FrameStatuses(Collections.singletonList(FrameStatus.GUTTER))),
                Arguments.of(2, new FrameStatuses(Collections.singletonList(FrameStatus.TWO))),
                Arguments.of(9, new FrameStatuses(Collections.singletonList(FrameStatus.NINE)))
        );
    }

    @DisplayName("두번째 투구도 진행됐을 때 상황에 맞는 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("completedResource")
    void calculateCurrentStatusWhenFinished(
            int firstNumberOfHitPin, int secondNumberOfHitPin, FrameStatuses expectedStatus) {
        NormalFrameResult inProgressFrameResult = NormalFrameResult.firstBowl(firstNumberOfHitPin);
        NormalFrameResult finishedFrameResult = inProgressFrameResult.secondBowl(secondNumberOfHitPin);

        assertThat(finishedFrameResult.calculateCurrentStatus()).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> completedResource() {
        return Stream.of(
                Arguments.of(5, 5, new FrameStatuses(Arrays.asList(FrameStatus.FIVE, FrameStatus.SPARE))),
                Arguments.of(5, 0, new FrameStatuses(Arrays.asList(FrameStatus.FIVE, FrameStatus.GUTTER))),
                Arguments.of(5, 4, new FrameStatuses(Arrays.asList(FrameStatus.FIVE, FrameStatus.FOUR)))
        );
    }
}
