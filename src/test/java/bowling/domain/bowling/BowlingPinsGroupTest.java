package bowling.domain.bowling;

import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingPinsGroupTest {

    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("다음 투구로 넘어가는 next 요청을 보내면, 현재 넘어지지 않은 볼링핀만을 모아 리턴함")
    @Test
    public void next_6개() {
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        bowlingPinsGroup.hitByBall(4);

        BowlingPinsGroup nextPitchBowlingPinsGroup = bowlingPinsGroup.next(false);

        assertThat(nextPitchBowlingPinsGroup.getBowlingPinCounts())
                .isEqualTo(6);
    }

    @DisplayName("다음 프레임으로 넘어가는 next 요청을 보내면, 볼링핀 10개를 초기화하여 리턴함")
    @Test
    public void next_초기화() {
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        bowlingPinsGroup.hitByBall(4);
        BowlingPinsGroup nextPitchBowlingPinsGroup = bowlingPinsGroup.next(false);

        assertThat(nextPitchBowlingPinsGroup.getBowlingPinCounts()).isEqualTo(6);

        BowlingPinsGroup nextFrameBowlingPinsGroup = nextPitchBowlingPinsGroup.next(true);

        assertThat(nextFrameBowlingPinsGroup.getBowlingPinCounts()).isEqualTo(10);
    }

    @DisplayName("hit 요청을 보낼 때 현재 서있는 볼링핀 개수보다 많은 수를 입력하면 예외 발생")
    @Test
    public void hitByBall_예외() {
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        bowlingPinsGroup.hitByBall(4);
        BowlingPinsGroup nextPitchBowlingPinsGroup = bowlingPinsGroup.next(false);

        assertThatThrownBy(() -> {
            nextPitchBowlingPinsGroup.hitByBall(7);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PITCH);
    }
}
