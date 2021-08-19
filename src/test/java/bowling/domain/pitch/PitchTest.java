package bowling.domain.pitch;

import static bowling.domain.pitch.PitchResult.GUTTER;
import static bowling.domain.pitch.PitchResult.MISS;
import static bowling.domain.pitch.PitchResult.SPARE;
import static bowling.domain.pitch.PitchResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.pitch.exception.PitchResultCreateException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("투구 결과")
class PitchTest {

    public static Stream<Arguments> first() {
        return Stream.of(
            Arguments.of(10, STRIKE),
            Arguments.of(9, MISS),
            Arguments.of(1, MISS),
            Arguments.of(0, GUTTER)
        );
    }

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @MethodSource("first")
    public void create(final int number, final PitchResult expected) {
        // given

        // when
        final Pitch pitch = Pitch.first(number);

        // then
        assertThat(pitch.getPitchResult()).isEqualTo(expected);
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 투구 결과 초기 값")
    @ParameterizedTest
    @CsvSource({
        "-1",
        "11",
    })
    public void create_invalidNumber(final int number) {
        // given

        // when
        assertThrows(PitchResultCreateException.class, () -> Pitch.first(number));

        // then
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 투구 결과 초기 값 (문자열)")
    @ParameterizedTest
    @CsvSource({
        "a"
    })
    public void create_invalidString(final String number) {
        // given

        // when
        assertThrows(PitchResultCreateException.class, () -> Pitch.first(number));

        // then
    }

    public static Stream<Arguments> second() {
        return Stream.of(
            Arguments.of(Pitch.first(0), 10, SPARE),
            Arguments.of(Pitch.first(1), 9, SPARE),
            Arguments.of(Pitch.first(9), 1, SPARE),
            Arguments.of(Pitch.first(10), 0, SPARE),
            Arguments.of(Pitch.first(1), 0, GUTTER),
            Arguments.of(Pitch.first(0), 0, GUTTER)
        );
    }

    @DisplayName("[성공] 생성 - 두번째")
    @ParameterizedTest
    @MethodSource("second")
    public void create_second(final Pitch first, final int number, final PitchResult expected) {
        // given

        // when
        final Pitch pitch = first.second(number);

        // then
        assertThat(pitch.getPitchResult()).isEqualTo(expected);
    }
}
