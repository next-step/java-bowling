package bowling;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    public void create_test() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThat(normalFrame).isEqualTo(NormalFrame.first());
    }

    @Test
    public void next_create_test() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThat(normalFrame.next()).isEqualTo(NormalFrame.first().next());
    }
}
