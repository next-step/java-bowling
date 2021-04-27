package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.state.CannotCompleteScoreException;
import bowling.domain.engine.frame.state.CannotInitializeScoreException;
import bowling.domain.engine.frame.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    private State readyState;

    @BeforeEach
    void setUp() {
        readyState = new Ready();
    }

    @Test
    @DisplayName("스트라이크를 치면 Strike 상태로 이전된다.")
    void transitToStrike() {
        assertThat(readyState.transit(RollResult.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("스트라이크가 아니면 Playing 상태로 이전된다.")
    void transitToPlaying() {
        assertThat(readyState.transit(RollResult.of(7))).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(readyState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Ready 상태에서는 빈 문자열을 내보낸다.")
    void export() {
        assertThat(readyState.export()).isEmpty();
    }

    @Test
    @DisplayName("Ready 상태에서는 점수를 생성할 수 없다.")
    void cannotCreateScoreInReady() {
        assertThatThrownBy(() -> readyState.createScore()).isInstanceOf(CannotInitializeScoreException.class);
    }

    @Test
    @DisplayName("Ready 상태에서는 Score 에 추가 점수를 넣어 줄 수 없다.")
    void cannotCompleteScoreInReady() {
        Score score = Score.initStrikeScore();
        assertThatThrownBy(() -> readyState.addScoreTo(score)).isInstanceOf(CannotCompleteScoreException.class);
    }

}
