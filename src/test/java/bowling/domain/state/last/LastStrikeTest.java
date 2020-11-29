package bowling.domain.state.last;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("마지막 스트라이크 상태 테스트")
public class LastStrikeTest {
    State state = new LastStrike();

    @DisplayName("잘못된 상태")
    @Test
    public void invalidState() {
        assertThatThrownBy(() -> {
            state.record(10);
        }).isInstanceOf(InvalidFrameRecordActionException.class);
    }

    @DisplayName("점수")
    @Test
    public void score() {
        assertThat(state.getScore()).isEqualTo(Score.strike());
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        assertThat(state.isFinished()).isEqualTo(true);
    }

}