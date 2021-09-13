package bowling;

import bowling.domain.FinalFrame;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @Test
    public void create_test() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }
}
