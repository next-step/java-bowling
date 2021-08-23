package bowling.domain;

import static bowling.domain.PitchResult.GUTTER;
import static bowling.domain.PitchResult.MISS;
import static bowling.domain.PitchResult.SPARE;
import static bowling.domain.PitchResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.exception.PitchResultCreateException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("투구")
class PitchTest {

    static Stream<Arguments> first() {
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
    void create(final int number, final PitchResult expected) {
        // given

        // when
        final Pitch pitch = new Pitch(number);

        // then
        assertThat(pitch.getPitchResult()).isEqualTo(expected);
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 투구 결과 초기 값")
    @ParameterizedTest
    @CsvSource({
        "-1",
        "11",
    })
    void create_invalidNumber(final int number) {
        // given

        // when
        assertThrows(PitchResultCreateException.class, () -> new Pitch(number));

        // then
    }

    static Stream<Arguments> second() {
        return Stream.of(
            Arguments.of(new Pitch(0), 10, SPARE),
            Arguments.of(new Pitch(1), 9, SPARE),
            Arguments.of(new Pitch(9), 1, SPARE),
            Arguments.of(new Pitch(10), 0, SPARE),
            Arguments.of(new Pitch(1), 0, GUTTER),
            Arguments.of(new Pitch(0), 0, GUTTER)
        );
    }

    @DisplayName("[성공] 생성 - 두번째")
    @ParameterizedTest
    @MethodSource("second")
    void create_second(final Pitch first, final int number, final PitchResult expected) {
        // given

        // when
        final Pitch second = first.next(number);

        // then
        assertThat(second.getPitchResult()).isEqualTo(expected);
    }
}
