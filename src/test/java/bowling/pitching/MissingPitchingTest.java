package bowling.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.FirstPitching;
import bowling.domain.pitching.MissingPitching;
import bowling.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MissingPitchingTest {

    @DisplayName("두 번째 투구 시 MISS 가 나온 경우")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumber")
    void missingPitchingTest(FallenPinNumber first, FallenPinNumber second, String expect) {
        Pitching pitching = MissingPitching.of(first, second);
        assertThat(pitching.getPitchingDescription()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideFallenPinNumber() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(5), FallenPinNumber.of(2), "5|2"),
                Arguments.of(FallenPinNumber.of(8), FallenPinNumber.of(1), "8|1"),
                Arguments.of(FallenPinNumber.of(0), FallenPinNumber.of(3), "-|3")
        );
    }

    @DisplayName("두 번째 투구 시 잘못된 값을 입력한 경우 예외 처리")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumberForException")
    void missingPitchingExceptionTest(FallenPinNumber first, FallenPinNumber second) {
        assertThatThrownBy(() -> MissingPitching.of(first, second)).isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> provideFallenPinNumberForException() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(5), FallenPinNumber.of(10)),
                Arguments.of(FallenPinNumber.of(8), FallenPinNumber.of(10)),
                Arguments.of(FallenPinNumber.of(0), FallenPinNumber.of(10))
        );
    }
}
