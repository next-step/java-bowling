package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class PitchingStatusTest {

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(PitchingStatus.Ready, Boolean.FALSE),
                Arguments.of(PitchingStatus.Done, Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @DisplayName("완료 상태 확인 검증")
    @MethodSource("provideArguments")
    void isDone(PitchingStatus pitchingStatus, boolean expected) {
        then(pitchingStatus.isDone()).isEqualTo(expected);
    }
}
