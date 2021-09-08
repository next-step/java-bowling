package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LaneTest {
    @Test
    @DisplayName("스트라이크를 치면 바로 reset 됨")
    public void pitchStrike() {
        Lane lane = new Lane(new TenPitchStrategy());
        ShotResult shotResult = lane.pitch();

        assertThat(shotResult).isEqualTo(ShotResult.TEN);
        lane.pitch();
    }

    @Test
    @DisplayName("핀이 남아도 2번 치면 lane 이 reset 됨")
    public void pitchTwice() {
        Lane lane = new Lane(new FourPitchStrategy());
        lane.pitch();
        ShotResult shotResult = lane.pitch();

        assertThat(shotResult).isEqualTo(ShotResult.FOUR);
        lane.pitch();
    }
}