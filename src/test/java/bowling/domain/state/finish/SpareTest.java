package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpareTest {

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

}
