package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 상태에서 여러 상태를 담기 위한 복합 상태")
class ComplexStateTest {

    @DisplayName("복합 상태를 초기화 하는데는 아무런 정보가 필요없다")
    @Test
    void init() {
        assertThat(ComplexState.init()).isInstanceOf(ComplexState.class);
    }

    @DisplayName("복합 상태에서 공을 굴리면 마지막 상태가 변경 된다")
    @Test
    void downPins() {
        ComplexState complexState = ComplexState.init();

        List<State> states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(Preparation.class);

        complexState.downPins(DownedPins.from(5));
        states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(InProgress.class);

        complexState.downPins(DownedPins.from(5));
        states = complexState.getState();
        assertThat(states.get(states.size() - 1)).isInstanceOf(Spare.class);
    }

}
