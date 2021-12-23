package bowling;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameRoundNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void 프레임넘버는_1에서_10까지_가능_동등성가짐(int number) {
        assertThat(new FrameRoundNumber(number)).isEqualTo(new FrameRoundNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void 프레임넘버는_0이하거나_11개이상일수없다_예외발생(int number) {
        assertThatThrownBy(() -> new FrameRoundNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FrameRoundNumber.WRONG_FRAME_NUMBER_MESSAGE);
    }
}
