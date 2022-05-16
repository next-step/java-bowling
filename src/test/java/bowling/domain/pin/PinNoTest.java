package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.frameresult.Spare;
import bowling.domain.frameresult.Strike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinNoTest {

    @ParameterizedTest(name = "쓰러뜨린 핀 개수는 0부터 10이다.")
    @ValueSource(ints = {-1, 11})
    void validation(int no) {
        assertThatThrownBy(() -> PinNo.of(no))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cache() {
        assertThat(PinNo.of(0)).isSameAs(PinNo.of(0));
        assertThat(PinNo.of(10)).isSameAs(PinNo.of(10));
    }

    @Test
    void isMaxNo() {
        assertThat(PinNo.of(0).isMaxNo()).isFalse();
        assertThat(PinNo.of(10).isMaxNo()).isTrue();
    }

    @Test
    void isMinNo() {
        assertThat(PinNo.of(0).isMinNo()).isTrue();
        assertThat(PinNo.of(10).isMinNo()).isFalse();
    }

    @Test
    void canPlus() {
        assertThat(PinNo.of(8).canPlus(2)).isTrue();
        assertThat(PinNo.of(8).canPlus(3)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForPlus")
    void plus(int firstNo, int secondNo, Class<? extends FrameResult> result) {
        assertThat(PinNo.of(firstNo).plus(PinNo.of(secondNo))).isInstanceOf(result);
    }

    private static Stream<Arguments> provideArgumentsForPlus() {
        return Stream.of(
                Arguments.of(10, 0, Strike.class),
                Arguments.of(9, 1, Spare.class),
                Arguments.of(9, 0, Miss.class));
    }
}
