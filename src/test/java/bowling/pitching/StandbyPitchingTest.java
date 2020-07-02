package bowling.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.FirstPitching;
import bowling.domain.pitching.Pitching;
import bowling.domain.pitching.StandbyPitching;
import bowling.domain.pitching.StrikePitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StandbyPitchingTest {

    @DisplayName("첫 프레임 대기하는 테스트")
    @ParameterizedTest
    @MethodSource("provideFallenPinNumber")
    void standbyPitchingTest(FallenPinNumber first, Pitching expect) {
        Pitching pitching = new StandbyPitching();
        Pitching result = pitching.pitch(first);

        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> provideFallenPinNumber() {
        return Stream.of(
                Arguments.of(FallenPinNumber.of(10), StrikePitching.of(FallenPinNumber.of(10))),
                Arguments.of(FallenPinNumber.of(8), FirstPitching.of(FallenPinNumber.of(8)))
        );
    }
}
