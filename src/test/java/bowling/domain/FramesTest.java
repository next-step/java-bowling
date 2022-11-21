package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FramesTest {

    public static final int END_FRAME_INDEX = 10;

    @Test
    void 최초투구() {
        Frames frames = Frames.init();

        frames.bowling(10);
        Frame frame = frames.get(0);

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(1);
        assertThat(frame.getResult(0)).isEqualTo(Result.STRIKE);
    }

    @Test
    void 다음투구() {
        Frames frames = Frames.init();

        frames.bowling(5);
        frames.bowling(5);
        Frame frame = frames.get(0);

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(1);
        assertThat(frame.getResult(1)).isEqualTo(Result.SPARE);
    }

    @Test
    void 종료조건_마지막미스() {
        Frames frames = Frames.init();

        for (int i = 0; i < 19; i++) {
            frames.bowling(2);
        }
        frames.bowling(2);

        /*OutputView init = OutputView.init();
        init.print(Name.of("abc"), frames);*/

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(END_FRAME_INDEX);
        assertThat(frames.isNotEnd()).isFalse();
    }

    @Test
    void 종료조건_마지막_스페어_스트라이크() {
        Frames frames = Frames.init();

        for (int i = 0; i < 19; i++) {
            frames.bowling(2);
        }

        /*OutputView init = OutputView.init();
        init.print(Name.of("abc"), frames);*/

        frames.bowling(8);
        frames.bowling(10);

        //init.print(Name.of("abc"), frames);

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(END_FRAME_INDEX);
        assertThat(frames.isNotEnd()).isFalse();
    }

    @Test
    void 종료조건_마지막_3스트라이크() {
        Frames frames = Frames.init();

        for (int i = 0; i < 18; i++) {
            frames.bowling(2);
        }

        frames.bowling(10);
        frames.bowling(10);
        frames.bowling(10);

        /*OutputView init = OutputView.init();
        init.print(Name.of("abc"), frames);*/

        assertThat(frames.getCurrentFrameIndex()).isEqualTo(END_FRAME_INDEX);
        assertThat(frames.isNotEnd()).isFalse();
    }
}
