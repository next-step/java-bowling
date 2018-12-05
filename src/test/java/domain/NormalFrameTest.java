package domain;

import domain.enums.StatusPointEnum;
import domain.frames.NormalFrame;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class NormalFrameTest {

    @Test
    public void strike_test() {
        int strikeBall = 10;
        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(strikeBall);
        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.STRIKE);
    }

    @Test
    public void spare_test() {
        int firstBallCount = 8;
        int secondBallCount = 2;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.SPARE);
    }

    @Test
    public void miss_test() {
        int firstBallCount = 3;
        int secondBallCount = 5;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.MISS);
    }

    @Test
    public void gutter_first_test() {
        int firstBallCount = 0;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.FIRSTGUTTER);
    }

    @Test
    public void gutter_second_test() {
        int firstBallCount = 5;
        int secondBallCount = 0;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.SECONDGUTTER);
    }
}