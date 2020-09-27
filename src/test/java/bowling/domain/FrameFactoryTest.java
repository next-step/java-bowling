package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameFactoryTest {

    @Test
    void factoryTest() {

        Frame first = FrameFactory.first();

        assertThat(first).isInstanceOf(NormalFrame.class);
        assertThat(FrameFactory.last()).isInstanceOf(LastFrame.class);

        for (int i = 0; i < 9; i++) {
            assertThat(FrameFactory.next(first, i)).isInstanceOf(NormalFrame.class);
        }

        assertThat(FrameFactory.next(first, 9)).isInstanceOf(LastFrame.class);
    }
}
