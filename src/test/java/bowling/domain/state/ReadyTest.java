package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("준비 상태 테스트")
class ReadyTest {

    @DisplayName("10개를 쓰러뜨린 경우 스트라이크다.")
    @Test
    void readyToStrike() {
        Ready ready = new Ready();

        State result = ready.bowl(Pin.of(10));

        assertThat(result).isInstanceOf(Strike.class);
    }

    @ParameterizedTest(name = "{0}개를 쓰러뜨린 경우 FirstBowl 상태다.")
    @ValueSource(ints = {0, 9})
    void readyToFirstBowl(int input) {
        Ready ready = new Ready();

        State result = ready.bowl(Pin.of(input));

        assertThat(result).isInstanceOf(FirstBowl.class);
    }
}

