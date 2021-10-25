package bowling.domain.state.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.state.RunningCreateScoreException;
import bowling.exception.state.StateCannotCalculateScoreException;
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

    @Test
    @DisplayName("이전 Score를 받아 first Pin을 더해 반환할 수 있다.")
    void calculateAdditionalScoreTest() {

        // given
        Score score = Score.spare();
        State state = new FirstBowl(Pin.of(3));

        Score expected = Score.from(13, 0);

        // when
        Score result = state.calculateAdditionalScore(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("이전 Score를 받아 left가 남아있다면 exception이 발생해야 한다.")
    void calculateAdditionalScoreExceptionTest() {

        // given
        Score score = Score.strike();
        State state = new FirstBowl(Pin.of(3));

        // when & then
        assertThatExceptionOfType(StateCannotCalculateScoreException.class)
            .isThrownBy(() -> state.calculateAdditionalScore(score))
            .withMessageMatching("현재 Running상태는 더이상 Score를 계산할 수 없습니다.");
    }

    @Test
    @DisplayName("desc 반환 테스트")
    void descTest() {

        // given
        State state = new FirstBowl(Pin.of(4));

        String expected = "4";

        // when
        String result = state.desc();

        // then
        assertThat(result).isEqualTo(expected);
    }

}