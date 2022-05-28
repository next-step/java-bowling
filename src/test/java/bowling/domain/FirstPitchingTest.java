package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FirstPitchingTest {
    @Test
    @DisplayName("첫번째 투구에 대한 객체 생성을 확인한다")
    void checkedFirstPitchingObjectGenerate() {
        // given
        Pitching pitching = new Pitching(10);

        // when
        FirstPitching firstPitching = new FirstPitching(pitching);

        // then
        assertThat(firstPitching.getFirst()).isEqualTo(pitching);
    }

    @Test
    @DisplayName("두 번째 투구 결과의 합이 10개의 핀을 넘어서는 경우, 예외처리를 한다")
    void exceptionPitchingSumResultOverPins() {
        // given
        FirstPitching firstPitching = new FirstPitching(new Pitching(5));

        // when & then
        assertThatThrownBy(() -> firstPitching.bowl((new Pitching(6))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "첫 번째 투구 {0}, 두 번째 투구 {1}인 경우, {2}인 상태를 갖는다.")
    @MethodSource("providePitchingAndResultState")
    void checkedPitchingStatus(Pitching firstPitching, Pitching secondPitching, Class<State> expected) {
        // given
        State state = new FirstPitching(firstPitching);

        // when
        State result = state.bowl(secondPitching);

        assertThat(result).isInstanceOf(expected);
    }

    @Test
    @DisplayName("투구에 대한 symbol을 확인한다")
    void checkedPitchingSymbol() {
        // given
        State state = new FirstPitching(new Pitching(5));

        // when
        String symbol = state.symbol();

        // then
        assertThat(symbol).isEqualTo("5");
    }

    private static Stream<Arguments> providePitchingAndResultState() {
        return Stream.of(
                arguments(new Pitching(1), new Pitching(2), Miss.class),
                arguments(new Pitching(2), new Pitching(8), Spare.class)
        );
    }
}
