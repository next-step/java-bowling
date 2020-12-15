package bowling.model.state.finishedState;

import bowling.model.Pins;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {
    @Test
    void of_정상() {
        assertThat(Strike.from(Pins.from(10)));
    }

    @ParameterizedTest
    @MethodSource("strikeParams")
    void of_Strike의_조건에_맞지_않는_경우(Pins pins) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Strike.from(pins))
                .withMessage("Strike의 조건에 맞지 않습니다.");
    }

    @Test
    void isFinished() {
        assertThat(Strike.from(Pins.from(10)).isFinished()).isTrue();
    }

    @Test
    void bowling() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Strike.from(Pins.from(10)).bowling(1))
                .withMessage("해당 프레임에서는 더 이상 던질 수 없습니다.");
    }

    private static Stream<Arguments> strikeParams() {
        return IntStream.rangeClosed(0,9)
                .mapToObj(Pins::from)
                .map(Arguments::of);
    }
}