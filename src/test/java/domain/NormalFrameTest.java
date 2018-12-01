package domain;

import domain.enums.StatusPointEnum;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class NormalFrameTest {

    @Test
    public void strike_test() {
        int ballCount = 10;
        NormalFrame normalFrame = NormalFrame.first(ballCount);
        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.STRIKE);
    }

    @Test
    public void spare_test() {
        int firstBallCount = 8;
        int secondBallCount = 2;

        NormalFrame normalFrame = NormalFrame.first(firstBallCount);
        normalFrame.second(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.SPARE);
    }

    @Test
    public void miss_test() {
        int firstBallCount = 7;
        int secondBallCount = 0;

        NormalFrame normalFrame = NormalFrame.first(firstBallCount);
        normalFrame.second(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.MISS);
    }

    @Test
    public void gutter_test() {
        int firstBallCount = 0;
        int secondBallCount = 0;

        NormalFrame normalFrame = NormalFrame.first(firstBallCount);
        normalFrame.second(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isEqualTo(StatusPointEnum.GUTTER);
    }
}