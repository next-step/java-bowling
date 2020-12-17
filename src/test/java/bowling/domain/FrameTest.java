package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 9:44
 * Developer : Seo
 */
class FrameTest {
    @Test
    void constructor() {
        assertThat(new Frame(1).getFrameNo()).isEqualTo("01");
    }

    @Test
    void bowl() {
        Frame frame = new Frame(1);
        assertThat(frame.bowl(1)).isInstanceOf(Frame.class);
    }

    @Test
    void name() {
        Frame frame = new Frame(0);
        frame.bowl(1);
        assertThat(frame.getScore()).isNotNull();
        System.out.println(frame.getScore().get());
    }

    @Test
    void name2() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = new Frame(0);
        frames.add(frame);
        for (int frameNo = 1; frameNo < 11; frameNo++) {
            frames.add(frame.bowl(frameNo));
        }
        assertThat(frames.get(1)).isNotNull();
        assertThat(frames.get(1).getScore().get()).isNotNull();
    }

    @Test
    void strike() {
//        Score score = new Score(10);
//        Frame frame = new Frame(1);
//        assertThat(frame.isStrike(pins)).isTrue();
    }

    @Test
    void spare() {
//        Frame frame = new Frame(1);
//        assertThat(frame.isStrike(pins)).isFalse();
//        assertThat(frame.isSpare(new Score(1))).isTrue();
    }

    @Test
    void next() {
        Frame normalFrame = new Frame(1);
//        assertThat(normalFrame.nextFrame(new Score(10))).isNotNull().isInstanceOf(NormalFrame.class);

        Frame finalFrame = new Frame(9);
//        assertThat(finalFrame.nextFrame(new Score(10))).isNotNull().isInstanceOf(FinalFrame.class);
    }
}
