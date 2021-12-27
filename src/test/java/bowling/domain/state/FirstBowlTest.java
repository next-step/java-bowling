package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.PinsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FirstBowlTest {

    private static Stream<Arguments> provideFirstPinsSecondPinsAndResultState() {
        return Stream.of(
                arguments(Pins.create(3), Pins.create(1), Miss.class),
                arguments(Pins.create(3), Pins.create(7), Spare.class)
        );
    }

    @DisplayName("첫 투구 이후, 남은 핀들을 넘어선 핀들을 투구한 경우 예외")
    @Test
    void bowlFailed() {
        // given
        ThrowingState firstBowl = FirstBowl.create(PinsTest.FIVE);
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> firstBowl.bowl(PinsTest.TEN));
    }

    @DisplayName("한 프레임에서 볼링 투구이후 상태 확인")
    @ParameterizedTest
    @MethodSource("provideFirstPinsSecondPinsAndResultState")
    void bowl(Pins first, Pins second, Class<ThrowingState> expectedResultState) {
        // given
        ThrowingState firstBowl = FirstBowl.create(first);
        // when & then
        assertThat(firstBowl.bowl(second)).isInstanceOf(expectedResultState);
    }

    @DisplayName("한 프레임 종료된 상태 확인")
    @Test
    void isEnd() {
        // given
        ThrowingState firstBowl = FirstBowl.create(Pins.create(9));
        // when & then
        assertThat(firstBowl.isEnd()).isFalse();
    }

    @DisplayName("현재 상태의 symbol 검증")
    @Test
    void symbol() {
        // given
        ThrowingState firstBowl = FirstBowl.create(Pins.create(5));
        // when & then
        assertThat(firstBowl.symbol()).isEqualTo(String.valueOf(5));
    }
}
