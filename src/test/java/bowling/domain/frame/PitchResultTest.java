package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.frame.exception.PitchResultCreateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("투구 결과")
class PitchResultTest {

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @CsvSource({
        "0",
        "1",
        "9",
        "10",
    })
    public void create(final int number) {
        // given

        // when
        final PitchResult pitchResult = PitchResult.of(number);

        // then
        assertThat(pitchResult).isNotNull();
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
        assertThrows(PitchResultCreateException.class, () -> PitchResult.of(number));

        // then
    }
}
