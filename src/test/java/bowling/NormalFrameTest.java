package bowling;

import bowling.entity.*;
import bowling.entity.frame.NormalFrame;
import bowling.entity.score.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalFrameTest {
    @Test
    @DisplayName("스트라이크 프레임 생성")
    public void createStrikeFrame() {
        int frameNo = 1;
        Strike strike = new Strike();

        NormalFrame normalFrame = new NormalFrame(frameNo, strike);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, strike))).isTrue();
    }

    @Test
    @DisplayName("스페어 프레임 생성")
    public void createSpareFrame() {
        int frameNo = 1;
        Spare spare = new Spare(new Pin(5));

        NormalFrame normalFrame = new NormalFrame(frameNo, spare);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, spare))).isTrue();
    }

    @Test
    @DisplayName("미스 프레임 생성")
    public void createMissFrame() {
        int frameNo = 1;
        Miss miss = new Miss(new Pin(5), new Pin(4));

        NormalFrame normalFrame = new NormalFrame(frameNo, miss);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, miss))).isTrue();
    }
}
