package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    private State missState;
    private RollResult firstRoll;
    private RollResult secondRoll;

    @BeforeEach
    void setUp() {
        firstRoll = RollResult.of(7);
        secondRoll = RollResult.of(2);
        missState = new Miss(firstRoll, secondRoll);
    }

    @Test
    @DisplayName("Strike 상태는 더 이상 다른 상태로 바뀔 수 없다.")
    void cannotTransit() {
        assertThatThrownBy(() -> missState.transit(RollResult.of(0)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 true 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(missState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Miss 상태에서는 두 투구의 결과를 | 로 구분하여 표기하며, 이를 내보낸다.")
    void export() {
        assertThat(missState.export()).isEqualTo(firstRoll.export() + "|" + secondRoll.export());
    }

    @Test
    @DisplayName("첫 번째 투구가 스트라이크면 Miss 상태를 만들 수 없다.")
    void cannotCreateMissIfFirstRollIsStrike() {
        assertThatThrownBy(() -> new Miss(RollResult.of(10), RollResult.of(0)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 번째 투구에서 핀을 모두 제거했다면 Miss 상태를 만들 수 없다.")
    void cannotCreateMissIfPinsRemain() {
        assertThatThrownBy(() -> new Miss(firstRoll, RollResult.of(3)))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
