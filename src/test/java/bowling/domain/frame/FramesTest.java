package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-17 오전 8:06
 * Developer : Seo
 */
class FramesTest {
    @Test
    void init() {
        Frames frames = new Frames();
        assertThat(frames).isNotNull().isInstanceOf(Frames.class);
        assertThat(frames.size()).isEqualTo(1);

        assertThat(frames.get(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void next() {
        Frames frames = new Frames();
        frames.next(0);
        assertThat(frames.size()).isEqualTo(2);
        assertThat(frames.get(1)).isInstanceOf(NormalFrame.class);

        for (int i = 1; i < 10; i++) {
            frames.next(i);
        }
        assertThat(frames.get(9)).isInstanceOf(FinalFrame.class);
    }
}
