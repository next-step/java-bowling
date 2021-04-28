package bowling;

import bowling.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NormalFrameTest {
    @Test
    @DisplayName("스트라이크 프레임 생성")
    public void createStrikeFrame(){
        Strike strike = new Strike(new Pin(10));

        NormalFrame normalFrame = new NormalFrame(strike);

        assertThat(normalFrame.equals(new NormalFrame(strike))).isTrue();
    }

    @Test
    @DisplayName("스트라이크 아닌 프레임 생성")
    public void createNoStrikeFrame(){
        NormalScore normalScore = new NormalScore(new Pin(5));

        NormalFrame normalFrame = new NormalFrame(normalScore);

        assertThat(normalFrame.equals(new NormalFrame(normalScore))).isTrue();
    }

    @Test
    @DisplayName("스페어 프레임 생성")
    public void createSpareFrame(){
        NormalScore normalScore = new NormalScore(new Pin(5));
        Spare spare = new Spare(normalScore.pin(), new Pin(5));

        NormalFrame normalFrame = new NormalFrame(spare);

        assertThat(normalFrame.equals(new NormalFrame(spare))).isTrue();
    }


    @Test
    @DisplayName("미스 프레임 생성")
    public void createMissFrame(){
        NormalScore normalScore = new NormalScore(new Pin(5));
        Miss miss = new Miss(normalScore.pin(), new Pin(4));

        NormalFrame normalFrame = new NormalFrame(miss);

        assertThat(normalFrame.equals(new NormalFrame(miss))).isTrue();
    }
}
