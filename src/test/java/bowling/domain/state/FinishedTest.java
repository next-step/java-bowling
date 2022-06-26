package bowling.domain.state;

import bowling.exception.IllegalBowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinishedTest {

    @DisplayName("strike 상태에서는 추가 투구를 할 수 없습니다.")
    @Test
    void bowl_afterStrike() {
        State state = new Ready();
        AbstractState strike = state.bowl(10);

        assertThatThrownBy(() -> strike.bowl(5))
                .isInstanceOf(IllegalBowlException.class)
                .hasMessageContaining("실행할 수 없는 메서드 입니다.");
    }

    @DisplayName("spare 상태에서는 추가 투구를 할 수 없습니다.")
    @Test
    void bowl_afterSpare() {
        State state = new Ready();
        AbstractState firstBowl = state.bowl(9);
        AbstractState spare = firstBowl.bowl(1);

        assertThatThrownBy(() -> spare.bowl(5))
                .isInstanceOf(IllegalBowlException.class)
                .hasMessageContaining("실행할 수 없는 메서드 입니다.");
    }

    @DisplayName("miss 상태에서는 추가 투구를 할 수 없습니다.")
    @Test
    void bowl_afterMiss() {
        State state = new Ready();
        AbstractState firstBowl = state.bowl(3);
        AbstractState miss = firstBowl.bowl(5);

        assertThatThrownBy(() -> miss.bowl(5))
                .isInstanceOf(IllegalBowlException.class)
                .hasMessageContaining("실행할 수 없는 메서드 입니다.");
    }

}