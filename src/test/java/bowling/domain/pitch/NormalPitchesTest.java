package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPitchesTest {

    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("투구를 하면 Pitch 객체를 생성하여 공에게 hit 메시지를 보내고, 내부 컬렉션에 추가함")
    @Test
    public void throwBall() {
        NormalPitches normalPitches = new NormalPitches();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        assertThat(normalPitches.getPitchCounts()).isEqualTo(0);

        normalPitches.throwBall(4, bowlingPinsGroup);

        assertThat(normalPitches.getPitchCounts()).isEqualTo(1);
    }

    @DisplayName("1~9번 프레임에서 투구를 했을 때 넘어뜨린 볼링 핀의 개수는 10개 이하")
    @Test
    public void throwBall_예외() {
        NormalPitches normalPitches = new NormalPitches();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        normalPitches.throwBall(3, bowlingPinsGroup);
        BowlingPinsGroup nextBowlingPinsGroup = bowlingPinsGroup.next(false);

        assertThatThrownBy(() -> {
            normalPitches.throwBall(8, nextBowlingPinsGroup);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PITCH);
    }

    @DisplayName("Strike인지 판별함")
    @Test
    public void isStrike_True() {
        NormalPitches normalPitches = new NormalPitches();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        normalPitches.throwBall(10, bowlingPinsGroup);

        assertThat(normalPitches.isStrike()).isTrue();
    }
}
