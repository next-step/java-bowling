package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;
import bowling.exception.state.SpareStateFirstPinStrikeException;
import bowling.exception.state.SpareStatePinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpareTest {

    @Test
    @DisplayName("생성 시 두 핀의 합이 10이 안되면 exception이 발생해야 한다.")
    void craeteBySparePinsTest() {

        // given
        Pin first = Pin.of(4);
        Pin second = Pin.of(5);

        // when & then
        assertThatExceptionOfType(SpareStatePinsException.class)
            .isThrownBy(() -> new Spare(first, second))
            .withMessageMatching("두 핀의 합이 10이 되지 않으면 Spare상태가 될 수 없습니다.");
    }

    @Test
    @DisplayName("첫번째 핀이 strike인데 spare상태로 만드려면 exception이 발생해야 한다.")
    void createByFirstStrikePinTest() {

        // given
        Pin first = Pin.of(10);
        Pin second = Pin.of(0);

        // when & then
        assertThatExceptionOfType(SpareStateFirstPinStrikeException.class)
            .isThrownBy(() -> new Spare(first, second))
            .withMessageMatching("첫 핀이 스트라이크라면 Spare상태가 될 수 없습니다.");
    }

    @Test
    @DisplayName("현재 상태가 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        State state = new Spare(Pin.of(5), Pin.of(5));

        // when
        boolean result = state.isFinished();

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Spare는 더이상 bowling을 던질 수 없다.")
    void bowlExceptionTest() {

        // given
        State state = new Spare(Pin.of(5), Pin.of(5));

        // when & then
        assertThatExceptionOfType(FinishStateBowlException.class)
            .isThrownBy(() -> state.bowl(Pin.of(0)))
            .withMessageMatching("종료된 상태에서 더이상 볼링을 던질 수 없습니다.");
    }

    @Test
    @DisplayName("이전 Score의 left가 1일 때 first만 업데이트할 수 있다.")
    void calculateAdditionalScoreByLeftOneTest() {

        // given
        Score score = Score.from(10, 1);
        Pin first = Pin.of(3);
        Pin second = Pin.of(7);
        State state = new Spare(first, second);

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
        Pin second = Pin.of(7);
        State state = new Spare(first, second);

        Score expected = Score.from(20, 0);

        // when
        Score result = state.calculateAdditionalScore(score);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
