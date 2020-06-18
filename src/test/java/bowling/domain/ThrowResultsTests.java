package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;
import bowling.domain.exceptions.TooManyThrowResultsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ThrowResultsTests {
    @DisplayName("ThrowResult 컬렉션 전달 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<ThrowResult> throwResultList
                = Arrays.asList(new ThrowResult(5), new ThrowResult(5));

        assertThat(new ThrowResults(throwResultList)).isEqualTo(new ThrowResults(throwResultList));
    }

    @DisplayName("최대 2개까지만 관리할 수 있다.")
    @Test
    void createValidationTest() {
        List<ThrowResult> invalidList = Arrays.asList(
                new ThrowResult(2), new ThrowResult(2), new ThrowResult(1));

        assertThatThrownBy(() -> new ThrowResults(invalidList))
                .isInstanceOf(TooManyThrowResultsException.class);
    }

    @DisplayName("던져서 맞춘 핀 수의 총합은 0 ~ 10을 벗어날 수 없다.")
    @ParameterizedTest
    @MethodSource("invalidTotalCounts")
    void totalHitPinValidationTest(ThrowResult first, ThrowResult second) {
        List<ThrowResult> invalidList = Arrays.asList(first, second);

        assertThatThrownBy(() -> new ThrowResults(invalidList))
                .isInstanceOf(InvalidNumberOfHitPinException.class);
    }
    public static Stream<Arguments> invalidTotalCounts() {
        return Stream.of(
                Arguments.of(new ThrowResult(5), new ThrowResult(9)),
                Arguments.of(new ThrowResult(5), new ThrowResult(6))
        );
    }
}
