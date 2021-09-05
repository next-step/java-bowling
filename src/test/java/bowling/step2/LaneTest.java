package bowling.step2;

import bowling.step2.domain.Lane;
import org.junit.jupiter.api.Test;

public class LaneTest {
    @Test
    public void pitchSuccess() {
        //given
        Lane lane = Lane.of("ttt");

        //when
        lane.pitch(5);
        lane.pitch(5);

        //then
    }
}
