package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RollingsValidationTest {

    @ParameterizedTest(name = "투구별 점수 검증 {index} [{arguments}]")
    @MethodSource("rollings_validation")
    @DisplayName("투구별 점수 검증")
    void rollings_validation(int first, int second, RollingsValidation expected) {
        //given

        //when
        RollingsValidation rollingsValidation = RollingsValidation.of(first, second);

        //then
        assertThat(rollingsValidation).isEqualTo(expected);

    }

    private static Stream<Arguments> rollings_validation() {
        return Stream.of(
                Arguments.of(10, 0, RollingsValidation.NONE),
                Arguments.of(5, 6, RollingsValidation.EXCEED_LIMIT_SUM),
                Arguments.of(10, 1, RollingsValidation.POST_STRIKE_ROLLING),
                Arguments.of(10, 0, RollingsValidation.NONE),
                Arguments.of(0, 10, RollingsValidation.NONE),
                Arguments.of(5, 4, RollingsValidation.NONE),
                Arguments.of(0, 0, RollingsValidation.NONE)
        );
    }
}
