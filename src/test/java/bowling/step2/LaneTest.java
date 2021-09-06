package bowling.step2;

import bowling.step2.domain.Lane;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LaneTest {
    @Test
    @DisplayName("공 던지기 성공")
    public void pitchSuccess() {
        //given
        Lane lane = Lane.of("ttt");

        //when
        lane.pitch(5);
        lane.pitch(5);

        //then
        assertThat(lane.current().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("공 던지기 성공하고 새로운 프레임을 만든다면 프레임의 갯수는 2")
    public void pitchSuccess2() {
        //given
        Lane lane = Lane.of("ttt");

        //when
        lane.pitch(5);
        lane.pitch(5);
        lane.nextFrame();
        lane.pitch(5);

        //then
        assertThat(lane.current().size()).isEqualTo(2);
    }
}
