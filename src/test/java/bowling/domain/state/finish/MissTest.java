package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;
import bowling.exception.state.MissStateCrerateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

    @Test
    @DisplayName("현재 상태가 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        State state = new Miss(Pin.of(5), Pin.of(1));

        // when
        boolean result = state.isFinished();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Miss는 더이상 bowling을 던질 수 없다.")
    void bowlExceptionTest() {

        // given
        State state = new Miss(Pin.of(5), Pin.of(1));

        // when & then
        assertThatExceptionOfType(FinishStateBowlException.class)
            .isThrownBy(() -> state.bowl(Pin.of(0)))
            .withMessageMatching("종료된 상태에서 더이상 볼링을 던질 수 없습니다.");
    }

    @Test
    @DisplayName("두 핀의 합이 10이 되면 exception이 반환되어야 한다.")
    void createMissExceptionTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(7);

        // when & then
        assertThatExceptionOfType(MissStateCrerateException.class)
            .isThrownBy(() -> new Miss(first, second))
            .withMessageMatching("Miss 상태는 두 핀의 합이 10이 넘을 수 없다.");
    }

    @Test
    @DisplayName("Score객체를 반환할 수 있다.")
    void createScoreTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(4);
        Miss miss = new Miss(first, second);

        Score expected = Score.miss(first.sum(second));

        // when
        Score result = miss.createScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("이전 Score의 left가 1일 때 first만 업데이트할 수 있다.")
    void calculateAdditionalScoreByLeftOneTest() {

        // given
        Score score = Score.from(10, 1);
        Pin first = Pin.of(3);
        Pin second = Pin.of(5);
        State state = new Miss(first, second);

        Score expected = Score.from(13, 0);

        // when
        Score result = state.calculateAdditionalScore(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("이전 Score의 left가 2이면 second까지 업데이트할 수 있다.")
    void calculateAdditionalScoreByLeftTwoTest() {

        // given
        Score score = Score.from(10, 2);
        Pin first = Pin.of(3);
        Pin second = Pin.of(5);
        State state = new Miss(first, second);

        Score expected = Score.from(18, 0);

        // when
        Score result = state.calculateAdditionalScore(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

}