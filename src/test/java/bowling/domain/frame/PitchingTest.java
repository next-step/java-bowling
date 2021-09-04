package bowling.domain.frame;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchingTest {

    @Test
    @DisplayName("초구 스트라이크")
    void strike() {
        //given
        Pitching pitching = Pitching.first(10);

        //when

        //then
        assertThat(pitching.isStrike()).isTrue();

    }

    @ParameterizedTest(name = "스페어 처리 {index} [{arguments}]")
    @CsvSource(value = {
            "0,10",
            "1,9",
            "9,1"
    })
    @DisplayName("스페어 처리")
    void spare(int first, int second) {
        //given
        Pitching pitching = Pitching.first(first).second(second);

        //when

        //then
        assertThat(pitching.isSpare()).isTrue();

    }

    @ParameterizedTest(name = "점수 합계 {index} [{arguments}]")
    @CsvSource(value = {
            "10,0,10",
            "0,10,10",
            "0,0,0"
    })
    @DisplayName("두 번의 투구 점수 합계")
    void sum(int first, int second, int expected) {
        //given
        Pitching pitching = Pitching.first(first).second(second);

        //when

        //then
        assertThat(pitching.sum()).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("invalid")
    void invalid(int first, int second, String expected) {
        //given
        Pitching pitching = Pitching.first(first);

        //when
        ThrowableAssert.ThrowingCallable actual = () -> pitching.second(second);

        //then
        assertThatThrownBy(actual).isInstanceOf(PitchingException.class)
                .hasMessage(expected);

    }

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(5, 6, PitchingValidation.EXCEED_LIMIT_SUM.message()),
                Arguments.of(10, 1, PitchingValidation.POST_STRIKE_PITCHING.message())
        );
    }

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @CsvSource(value = {
            "10,true",
            "0,false"
    })
    @DisplayName("모든 투구 완료")
    void complete_pitching(int firstPitching, boolean expected) {
        //given

        //when
        Pitching pitching = Pitching.first(firstPitching);

        //then
        assertThat(pitching.allPitched()).isEqualTo(expected);

    }

}
