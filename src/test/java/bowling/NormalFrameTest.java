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
    public void createStrikeFrame(){
        int frameNo = 1;
        Strike strike = new Strike(new Pin(10));

        NormalFrame normalFrame = new NormalFrame(frameNo, strike);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, strike))).isTrue();
    }

    @Test
    @DisplayName("스페어 프레임 생성")
    public void createSpareFrame(){
        int frameNo = 1;
        NormalScore normalScore = new NormalScore(new Pin(5));
        Spare spare = new Spare(normalScore.score(), new Pin(5));

        NormalFrame normalFrame = new NormalFrame(frameNo, spare);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, spare))).isTrue();
    }


    @Test
    @DisplayName("미스 프레임 생성")
    public void createMissFrame(){
        int frameNo = 1;
        NormalScore normalScore = new NormalScore(new Pin(5));
        Miss miss = new Miss(normalScore.score(), new Pin(4));

        NormalFrame normalFrame = new NormalFrame(frameNo, miss);

        assertThat(normalFrame.equals(new NormalFrame(frameNo, miss))).isTrue();
    }
}
