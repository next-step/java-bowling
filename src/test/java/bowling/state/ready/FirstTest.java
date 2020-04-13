package bowling.state.ready;

import bowling.state.State;
import bowling.state.finish.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FirstTest {

    @DisplayName("첫번째 시도에 10개의 핀을 쓰러트리면 스트라이크가 된다.")
    @Test
    public void strikeTest() {
        First first = First.of();
        State strike = first.bowl(10);

        assertThat(strike).isInstanceOf(Strike.class);
    }

    @DisplayName("첫번째 시도에 10개의 핀을 쓰러트리지 못하면 Second 상태가 된다.")
    @Test
    public void secondTest() {
        State second = First.of().bowl(9);

        assertThat(second).isInstanceOf(Second.class);
    }

    @DisplayName("첫번째 시도로 프레임이 끝났는지의 상태를 알 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"10, true", "9, false"})
    public void isDoneTest(int felledPins, boolean expected) {
        First first = First.of();
        State frameState = first.bowl(felledPins);

        assertThat(frameState.isFinished()).isEqualTo(expected);
    }

}
