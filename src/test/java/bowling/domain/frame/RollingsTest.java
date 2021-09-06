package bowling.domain.frame;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RollingsTest {

    @Test
    @DisplayName("초구 스트라이크")
    void strike() {
        //given
        Rollings rollings = Rollings.first(10);

        //when

        //then
        assertThat(rollings.isStrike()).isTrue();

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
        Rollings rollings = Rollings.first(first).second(second);

        //when

        //then
        assertThat(rollings.isSpare()).isTrue();

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
        Rollings rollings = Rollings.first(first).second(second);

        //when

        //then
        assertThat(rollings.sum()).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("invalid")
    void invalid(int first, int second, String expected) {
        //given
        Rollings rollings = Rollings.first(first);

        //when
        ThrowableAssert.ThrowingCallable actual = () -> rollings.second(second);

        //then
        assertThatThrownBy(actual).isInstanceOf(RollingsException.class)
                .hasMessage(expected);

    }

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(5, 6, RollingsValidation.EXCEED_LIMIT_SUM.message()),
                Arguments.of(10, 1, RollingsValidation.POST_STRIKE_ROLLING.message())
        );
    }

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @CsvSource(value = {
            "10,true",
            "0,false"
    })
    @DisplayName("모든 투구 완료")
    void complete_rollings(int firstRolling, boolean expected) {
        //given

        //when
        Rollings rollings = Rollings.first(firstRolling);

        //then
        assertThat(rollings.allRolled()).isEqualTo(expected);

    }

}
