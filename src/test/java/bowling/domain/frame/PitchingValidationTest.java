package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PitchingValidationTest {

    @ParameterizedTest(name = "투구별 점수 검증 {index} [{arguments}]")
    @MethodSource("pitching_validation")
    @DisplayName("투구별 점수 검증")
    void pitching_validation(int first, int second, PitchingValidation expected) {
        //given

        //when
        PitchingValidation pitchingValidation = PitchingValidation.of(first, second);

        //then
        assertThat(pitchingValidation).isEqualTo(expected);

    }

    private static Stream<Arguments> pitching_validation() {
        return Stream.of(
                Arguments.of(10, 0, PitchingValidation.NONE),
                Arguments.of(5, 6, PitchingValidation.EXCEED_LIMIT_SUM),
                Arguments.of(11, 11, PitchingValidation.EXCEED_LIMIT_PER_PITCHING),
                Arguments.of(10, 1, PitchingValidation.POST_STRIKE_PITCHING),
                Arguments.of(-1, 0, PitchingValidation.MINUS),
                Arguments.of(10, 0, PitchingValidation.NONE),
                Arguments.of(0, 10, PitchingValidation.NONE),
                Arguments.of(5, 4, PitchingValidation.NONE),
                Arguments.of(0, 0, PitchingValidation.NONE)
        );
    }
}
