package bowling.domain.state.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import bowling.exception.state.RunningCreateScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("현재 상태가 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        State state = new Ready();

        // when
        boolean result = state.isFinished();

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("Ready상태에서 Score 생성하려고하면 exception이 발생해야 한다.")
    void createScoreExceptionTest() {

        // given
        State state = new Ready();

        // when & then
        assertThatExceptionOfType(RunningCreateScoreException.class)
            .isThrownBy(() -> state.createScore())
            .withMessageMatching("running 상태는 score를 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("볼링을 했을 때 Pin이 strike면 Strike 상태를 반환해야 한다.")
    void bowlByStrikePinTest() {

        // given
        State state = new Ready();
        Pin pin = Pin.of(10);

        // when
        State result = state.bowl(pin);

        // then
        assertThat(result).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("볼링을 했을 때 Pin이 strike가 아니면 현재 핀을 가진 first bowl 상태를 반환해야 한다.")
    void bowlByNotStrikePinTest() {

        // given
        State state = new Ready();
        Pin pin = Pin.of(3);

        // when
        State result = state.bowl(pin);

        // then
        assertThat(result).isInstanceOf(FirstBowl.class);
    }

}