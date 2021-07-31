package bowling.domain.state;

import bowling.domain.pin.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 상태 표현을 위한 클래스 테스트")
class StateTest {

    @DisplayName("상태에 쓰러진 핀 정보를 넘기면 다음 상태를 넘긴다")
    @Test
    void downPins() {
        State someState = new State();
        DownedPin downedPin = DownedPin.from(5);

        assertThat(someState.downPins(downedPin)).isInstanceOf(State.class);
    }

}
