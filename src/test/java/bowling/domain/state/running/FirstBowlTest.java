package bowling.domain.state.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.state.RunningCreateScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    @DisplayName("첫번째 볼링은 종료가 되지 않아야 한다.")
    void isFinishedFalseTest() {

        // given
        State state = new FirstBowl(Pin.of(5));

        // when
        boolean result = state.isFinished();

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("First Bowl상태에서 Score 생성하려고하면 exception이 발생해야 한다.")
    void createScoreExceptionTest() {

        // given
        State state = new FirstBowl(Pin.of(5));

        // when & then
        assertThatExceptionOfType(RunningCreateScoreException.class)
            .isThrownBy(() -> state.createScore())
            .withMessageMatching("running 상태는 score를 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("pin을 받아 현재 핀과 계산해서 spare라면 Spare 객체를 반환해야 한다.")
    void bowlBySparePinTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(7);
        State state = new FirstBowl(first);

        // when
        State result = state.bowl(second);

        // then
        assertThat(result).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("pin을 받아 현재 핀과 계산해서 spare가 아니라면 Miss 객체를 반환해야 한다.")
    void bowlByNonSparePinTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(5);
        State state = new FirstBowl(first);

        // when
        State result = state.bowl(second);

        // then
        assertThat(result).isInstanceOf(Miss.class);
    }

}