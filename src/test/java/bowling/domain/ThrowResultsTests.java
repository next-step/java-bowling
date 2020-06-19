package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.exceptions.TooManyThrowResultsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ThrowResultsTests {
    @DisplayName("ThrowResult 컬렉션 전달 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<NumberOfHitPin> numberOfHitPinList
                = Arrays.asList(new NumberOfHitPin(5), new NumberOfHitPin(5));

        assertThat(new ThrowResults(numberOfHitPinList)).isEqualTo(new ThrowResults(numberOfHitPinList));
    }

    @DisplayName("맞춘 핀 수들을 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createByFactoryTest() {
        int firstNumberOfHitPin = 3;
        int secondNumberOfHitPin = 4;

        assertThat(ThrowResults.of(firstNumberOfHitPin, secondNumberOfHitPin))
                .isEqualTo(ThrowResults.of(firstNumberOfHitPin, secondNumberOfHitPin));
    }

    @DisplayName("최대 2개까지만 관리할 수 있다.")
    @Test
    void createValidationTest() {
        List<NumberOfHitPin> invalidList = Arrays.asList(
                new NumberOfHitPin(2), new NumberOfHitPin(2), new NumberOfHitPin(1));

        assertThatThrownBy(() -> new ThrowResults(invalidList))
                .isInstanceOf(TooManyThrowResultsException.class);
    }

    @DisplayName("던져서 맞춘 핀 수의 총합은 0 ~ 10을 벗어날 수 없다.")
    @ParameterizedTest
    @MethodSource("invalidTotalCounts")
    void totalHitPinValidationTest(NumberOfHitPin first, NumberOfHitPin second) {
        List<NumberOfHitPin> invalidList = Arrays.asList(first, second);

        assertThatThrownBy(() -> new ThrowResults(invalidList))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }
    public static Stream<Arguments> invalidTotalCounts() {
        return Stream.of(
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(9)),
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(6))
        );
    }

    @DisplayName("스트라이크인 경우를 만들 수 있다.")
    @Test
    void strikeTest() {
        assertThat(ThrowResults.strike())
                .isEqualTo(new ThrowResults(Collections.singletonList(new NumberOfHitPin(10))));
    }
}
