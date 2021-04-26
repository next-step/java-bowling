package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FramesTest {
    @Test
    public void tempTest() {
        // given
        Frames frames = new Frames(Arrays.asList(NormalFrame.valueOf(3), FinalFrame.valueOf(3)));
        // when

        // then

    }
}
