package bowling.domain;

import bowling.type.PlayStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void testFrameStatusWithStrike(){
        assertThat(new NormalFrame(new Scores(Arrays.asList(10)))).isEqualTo(new NormalFrame(1, new Scores(Arrays.asList(10)), PlayStatus.END));
    }

    @Test
    void testFrameStatusWithScore(){
        assertThat(new NormalFrame(new Scores(Arrays.asList(5)))).isEqualTo(new NormalFrame(1, new Scores(Arrays.asList(5)), PlayStatus.IN_PROGRESS));
    }

}
