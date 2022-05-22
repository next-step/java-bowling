package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstBowlTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 10, 11})
    void validation(int firstPin) {
        assertThatThrownBy(() -> new FirstBowl(firstPin))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void bowl_WhenSumIsOverMax_ThrowsIllegalArgumentException() {
        FirstBowl firstBowl = new FirstBowl(5);

        assertThatThrownBy(() -> firstBowl.bowl(Pin.of(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForBowl")
    void bowl(Pin firstPin, Pin secondPin, Class<? extends Finished> clazz) {
        assertThat(new FirstBowl(firstPin).bowl(secondPin))
                .isInstanceOf(clazz);
    }

    private static Stream<Arguments> provideArgumentsForBowl() {
        return Stream.of(
                Arguments.of(Pin.of(4), Pin.of(5), Miss.class),
                Arguments.of(Pin.of(5), Pin.of(5), Spare.class));
    }

    @Test
    void additionalScore_ForSpare() {
        Score beforeScore = Score.ofSpare();
        FirstBowl firstBowl = new FirstBowl(5);

        Score afterScore = firstBowl.additionalScore(beforeScore);

        assertThat(afterScore.getScore()).isEqualTo(15);
    }
}
