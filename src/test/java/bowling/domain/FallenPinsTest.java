package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import bowling.domain.exception.InvalidCountOfPinException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FallenPinsTest {

    @Test
    void 쓰러진_볼링핀들_생성() {
        assertDoesNotThrow(() -> FallenPins.of(4));
    }

    @Test
    void 음수인_쓰러진_볼링핀들_생성_시도_시_예외_발생() {
        assertThatExceptionOfType(InvalidCountOfPinException.class)
                .isThrownBy(() -> FallenPins.of(-1));
    }

    @Test
    void 최대_볼링핀_수를_넘어서는_쓰러진_볼링핀들_생성_시도_시_예외_발생() {
        assertThatExceptionOfType(InvalidCountOfPinException.class)
                .isThrownBy(() -> FallenPins.of(15));
    }

    @ParameterizedTest
    @MethodSource("getDataOfIsAllPinFallen")
    void 볼링핀_전부_쓰러짐_확인(FallenPins fallenPins, boolean expected) {
        assertThat(fallenPins.isAllPinFallen()).isEqualTo(expected);
    }

    @Test
    void 볼링핀_합치기() {
        FallenPins fallenPins1 = FallenPins.of(4);
        FallenPins fallenPins2 = FallenPins.of(5);
        assertThat(fallenPins1.merge(fallenPins2).getCountOfPin()).isEqualTo(9);
    }

    private static Stream<Arguments> getDataOfIsAllPinFallen() {
        return Stream.of(
                Arguments.arguments(FallenPins.of(10), true),
                Arguments.arguments(FallenPins.of(5), false)
        );
    }

}
