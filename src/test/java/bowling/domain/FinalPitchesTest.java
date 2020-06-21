package bowling.domain;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.pitch.FinalPitches;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalPitchesTest {

    @DisplayName("NormalPitches와 동일하게 투구 시 기록을 남기며, 투구 회수는 갱신됨")
    @Test
    public void bowl_추가() {
        FinalPitches finalPitches = new FinalPitches();

        assertThat(finalPitches.getPitchCounts()).isEqualTo(0);

        finalPitches.recordPitch(3);

        assertThat(finalPitches.getPitchCounts()).isEqualTo(1);
    }

    @DisplayName("1번 투구가 스트라이크가 아닌데 1, 2번 투구의 합이 10을 초과면 에러")
    @Test
    public void bowl_예외() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.recordPitch(4);

        assertThatThrownBy(() -> {
            finalPitches.recordPitch(8);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_FRAME_RESULT);
    }
}
