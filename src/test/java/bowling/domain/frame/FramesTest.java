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

        assertThat(frames.get()).isInstanceOf(NormalFrame.class);
    }
}
