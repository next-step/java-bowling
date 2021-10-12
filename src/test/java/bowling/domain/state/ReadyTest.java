package bowling.domain.state;

import bowling.domain.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {

    @DisplayName("Max Pin 플레이 시 다음 상태 Strike 반환")
    @Test
    void play_ReturnStrike_IfInputIsMaxPinCount() {
        State ready = StateFactory.ready();
        State nextState = ready.play(PinCount.MAX_PINS);
        assertThat(nextState).isExactlyInstanceOf(Strike.class);
    }

    @DisplayName("Max Pin 아닌 경우 플레이 시 다음 상태 OncePitched 반환")
    @Test
    void play_ReturnOncePitched_IfInputIsNotMaxPinCount() {
        State ready = StateFactory.ready();
        State nextState = ready.play(PinCount.of(9));
        assertThat(nextState).isExactlyInstanceOf(OncePitched.class);
    }
}
