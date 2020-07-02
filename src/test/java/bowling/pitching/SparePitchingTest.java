package bowling.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.MissingPitching;
import bowling.domain.pitching.Pitching;
import bowling.domain.pitching.SparePitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SparePitchingTest {

    @DisplayName("두 번째 투구 시 Spare 가 나온 경우")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumber")
    void sparePitchingTest(FallenPinNumber first, FallenPinNumber second, String expect) {
        Pitching pitching = SparePitching.of(first, second);
        assertThat(pitching.getPitchingDescription()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideFallenPinNumber() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(5), FallenPinNumber.of(5), "5|/"),
                Arguments.of(FallenPinNumber.of(8), FallenPinNumber.of(2), "8|/"),
                Arguments.of(FallenPinNumber.of(0), FallenPinNumber.of(10), "-|/")
        );
    }

}
