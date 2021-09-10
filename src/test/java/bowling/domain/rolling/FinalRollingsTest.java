package bowling.domain.rolling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalRollingsTest {

    @Test
    void construct() {
        //given
        FinalRollings finalRollings = FinalRollings.first(10);

        //when

        //then
        assertThat(finalRollings).isEqualTo(FinalRollings.first(10));

    }

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @MethodSource("all_rolled")
    @DisplayName("모든 투구 완료여부")
    void all_rolled(FinalRollings finalRollings, boolean expected) {
        //given

        //when
        boolean actual = finalRollings.allRolled();

        //then
        assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> all_rolled() {
        return Stream.of(
                Arguments.of(FinalRollings.first(10).roll(10).roll(10), true),
                Arguments.of(FinalRollings.first(0).roll(10).roll(10), true),
                Arguments.of(FinalRollings.first(10).roll(0).roll(10), true),
                Arguments.of(FinalRollings.first(1).roll(1), true),
                Arguments.of(FinalRollings.first(0).roll(0), true),
                Arguments.of(FinalRollings.first(0), false),
                Arguments.of(FinalRollings.first(9), false)
        );
    }

    @Test
    @DisplayName("모든 투구 이후 투구")
    void roll_exception() {
        //given
        FinalRollings finalRollings = FinalRollings.first(10).roll(10).roll(10);

        //when

        //then
        assertThatThrownBy(() -> finalRollings.roll(10)).isInstanceOf(FinalRollingsException.class)
                .hasMessage("모든 투구가 완료되어 투구하실 수 없습니다.");

    }



}
