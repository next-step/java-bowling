package bowling.domain.bowling;

import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BowlingPinsGroupTest {

    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("매 프레임의 첫 번째 투구 등 처음 칠 때 볼링 핀을 초기화 시켜 반환함")
    @Test
    public void initiateBowlingPinsGroup_초기화() {
        assertThatCode(() -> {
            BowlingPinsGroup.initiate();
        }).doesNotThrowAnyException();
    }

    @DisplayName("next 요청을 보내면, 현재 넘어지지 않은 볼링핀만을 모아 리턴함")
    @Test
    public void next_6개() {
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        bowlingPinsGroup.hitByBall(4);

        BowlingPinsGroup nextPitchBowlingPinsGroup = bowlingPinsGroup.next();

        assertThat(nextPitchBowlingPinsGroup.getBowlingPinCounts())
                .isEqualTo(6);
    }

    @DisplayName("hit 요청을 보낼 때 현재 서있는 볼링핀 개수보다 많은 수를 입력하면 예외 발생")
    @Test
    public void hitByBall_예외() {
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        bowlingPinsGroup.hitByBall(4);
        BowlingPinsGroup nextPitchBowlingPinsGroup = bowlingPinsGroup.next();

        assertThatThrownBy(() -> {
            nextPitchBowlingPinsGroup.hitByBall(7);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PITCH);
    }
}
