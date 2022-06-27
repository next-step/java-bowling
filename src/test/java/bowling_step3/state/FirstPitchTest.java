package bowling_step3.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling_step3.domain.Scores;
import bowling_step3.domain.state.FirstPitch;
import bowling_step3.domain.state.Miss;
import bowling_step3.domain.state.Spare;
import bowling_step3.domain.state.State;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstPitchTest {
    @Test
    public void bowlWhenSpare() {
        FirstPitch firstPitch = new FirstPitch(new Scores(new ArrayList(Arrays.asList(9)), 0));
        State state = firstPitch.pitch(1);
        assertThat(state instanceof Spare).isTrue();
    }

    @Test
    public void bowlWhenMiss() throws Exception {
        FirstPitch firstPitch = new FirstPitch(new Scores(new ArrayList(Arrays.asList(9)), 0));
        State state = firstPitch.pitch(0);
        assertThat(state instanceof Miss).isTrue();
    }
}
