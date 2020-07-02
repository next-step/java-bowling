package bowling.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.BonusPitching;
import bowling.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BonusPitchingTest {

    @DisplayName("10 Frame 에서 투구할 때 스트라이크 / 스페어 쳐서 보너스 투구하는 경우")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumber")
    void bonusPitchingTest(FallenPinNumber first, FallenPinNumber second, FallenPinNumber third, String expect) {
        Pitching pitching = BonusPitching.of(first, second, third);
        assertThat(pitching.getPitchingDescription()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideFallenPinNumber() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(10), FallenPinNumber.of(2), FallenPinNumber.of(10), "X|2|X"),
                Arguments.of(FallenPinNumber.of(8), FallenPinNumber.of(1), null, "8|1"),
                Arguments.of(FallenPinNumber.of(10), FallenPinNumber.of(10), FallenPinNumber.of(10), "X|X|X"),
                Arguments.of(FallenPinNumber.of(2), FallenPinNumber.of(8), FallenPinNumber.of(2), "2|/|2")
        );
    }
}
