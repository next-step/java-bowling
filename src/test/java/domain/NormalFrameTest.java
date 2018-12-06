package domain;

import domain.frames.NormalFrame;
import domain.status.Gutter;
import domain.status.Miss;
import domain.status.Spare;
import domain.status.Strike;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class NormalFrameTest {

    @Test
    public void strike_test() {
        int strikeBall = 10;
        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(strikeBall);
        assertThat(normalFrame.askFramePoint()).isInstanceOf(Strike.class);
    }

    @Test
    public void spare_test() {
        int firstBallCount = 8;
        int secondBallCount = 2;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isInstanceOf(Spare.class);
    }

    @Test
    public void miss_test() {
        int firstBallCount = 3;
        int secondBallCount = 5;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isInstanceOf(Miss.class);
    }

    @Test
    public void gutter_first_test() {
        int firstBallCount = 0;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);

        assertThat(normalFrame.askFramePoint()).isInstanceOf(Gutter.class);
    }

    @Test
    public void gutter_second_test() {
        int firstBallCount = 5;
        int secondBallCount = 0;

        NormalFrame normalFrame = NormalFrame.frameFactory(1);
        normalFrame.bowling(firstBallCount);
        normalFrame.bowling(secondBallCount);

        assertThat(normalFrame.askFramePoint()).isInstanceOf(Gutter.class);
    }
}