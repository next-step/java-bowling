package bowling.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.FirstPitching;
import bowling.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FirstPitchingTest {

    @DisplayName("첫 번째 투구 테스트")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumber")
    void firstPitchingTest(FallenPinNumber first, String expect) {
        Pitching pitching = FirstPitching.of(first);
        assertThat(pitching.getPitchingDescription()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideFallenPinNumber() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(10), "X"),
                Arguments.of(FallenPinNumber.of(8), "8"),
                Arguments.of(FallenPinNumber.of(0), "-")
        );
    }
}
