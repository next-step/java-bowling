package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("공 상태")
class BallStateTest {

    @Test
    @DisplayName("보너스 갯수 기본 값은 0")
    void bonusCount() {
        //given
        BallState ballState = new BallState() {
            @Override
            public boolean isEnd() {
                return false;
            }

            @Override
            public BallState state(Pins countOfHit) {
                return null;
            }
        };
        //when, then
        assertThat(ballState.bonusCount()).isZero();
    }
}
