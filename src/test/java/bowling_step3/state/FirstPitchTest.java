package bowling_step3.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling_step3.domain.Scores;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstPitchTest {
    @Test
    public void bowlWhenSpare() {
        FirstPitch firstPitch = new FirstPitch(new Scores(new ArrayList(Arrays.asList(9)), 0));
        State state = firstPitch.pitch(1);
        assertThat(state instanceof Spare).isTrue();
    }

//    @Test
//    public void bowlWhenMiss() throws Exception {
//        FirstPitch firstPitch = new FirstPitch(Pins.pitch(9));
//        State state = firstPitch.pitch(0);
//        assertThat(state instanceof Miss).isTrue();
//    }
}
