package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SetTest {

    private Pin firstPins;
    private State state;

    @Test
    @DisplayName("투구 종료 여부 확인 - 미종료")
    void isFinished() {
        state = Set.init();
        assertThat(state.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫번째 볼링 결과 - 스트라이크")
    void firstBowlIsStrike() {
        firstPins = Pin.bowl("10");
        state = Set.init().bowl(firstPins);
        assertThat(state instanceof Strike);
        assertThat(state.isFinish()).isTrue();
    }

    @Test
    @DisplayName("첫번째 볼링 결과 - 다음 회차로 이동")
    void firstBowlIsNext() {
        firstPins = Pin.bowl("3");
        state = Set.init().bowl(firstPins);
        assertThat(state instanceof Next);
    }

}
