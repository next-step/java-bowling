package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class FrameTest {

    @Test
    @DisplayName("프레임은 고정으로 10개 만든다")
    public void frame() throws Exception {
        //given
        List<Frame> frame = FrameFactory.of();

        //when
        
        //then
        assertThat(frame.size()).isEqualTo(10);
    }
}
