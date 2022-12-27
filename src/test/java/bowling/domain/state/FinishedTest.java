package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class FinishedTest {

    @ParameterizedTest
    @MethodSource("finishedInstanceProvider")
    void 종료_여부_판단(Finished status) {
        assertThat(status.isFinished()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("finishedInstanceProvider")
    void 게임진행_예외발생(Finished status) {
        assertThatThrownBy(() -> status.bowl(new Pin(0)))
                .isInstanceOf(IllegalStateException.class);
    }

    static Stream<Arguments> finishedInstanceProvider() {
        return Stream.of(
                Arguments.of(new Strike()),
                Arguments.of(new Spare(new Pin(0))),
                Arguments.of(new Miss(new Pin(0), new Pin(0)))
        );
    }
}