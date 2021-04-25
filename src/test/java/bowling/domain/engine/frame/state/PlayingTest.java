package bowling.domain.engine.frame.state;

import bowling.domain.engine.roll.Roll;
import bowling.domain.engine.roll.RollResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingTest {

    private State playingState;
    private RollResult firstRoll;

    @BeforeEach
    void setUp() {
        firstRoll = Roll.result(8);
        playingState = new Playing(firstRoll);
    }

    @Test
    @DisplayName("나머지 핀을 쓰러트리면 Spare 상태로 이전된다.")
    void transitToSpare() {
        assertThat(playingState.transit(Roll.result(2))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("핀이 남으면 Miss 상태로 이전된다.")
    void transitToMiss() {
        assertThat(playingState.transit(Roll.result(1))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(playingState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Playing 상태에서는 첫 번째 결과만 내보낸다.")
    void export() {
        assertThat(playingState.export()).isEqualTo("8");
    }
    
}
