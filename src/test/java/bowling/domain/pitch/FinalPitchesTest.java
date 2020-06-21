package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalPitchesTest {

    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("NormalPitches와 동일하게 투구 시 기록을 남기며, 투구 회수는 갱신됨")
    @Test
    public void throwBall_추가() {
        FinalPitches finalPitches = new FinalPitches();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        assertThat(finalPitches.getPitchCounts()).isEqualTo(0);

        finalPitches.throwBall(3, bowlingPinsGroup);

        assertThat(finalPitches.getPitchCounts()).isEqualTo(1);
    }

    @DisplayName("마지막 프레임의 첫 두 번의 투구 중 스트라이크가 포함되면 보너스 투구가 가능함")
    @Test
    public void isAvailableToPitchBonus_True_원스트라이크() {
        FinalPitches finalPitches = new FinalPitches();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        finalPitches.throwBall(10, bowlingPinsGroup);

        assertThat(finalPitches.isAvailableToPitchBonus()).isTrue();
    }

    @DisplayName("마지막 프레임의 첫 두 번의 투구 중 스트라이크가 두 번 포함되면 보너스 투구가 가능함")
    @Test
    public void isAvailableToPitchBonus_True_투스트라이크() {
        FinalPitches finalPitches = new FinalPitches();

        for (int i = 0; i < 2; i++) {
            BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
            finalPitches.throwBall(10, bowlingPinsGroup);
        }

        assertThat(finalPitches.isAvailableToPitchBonus()).isTrue();
    }
}
