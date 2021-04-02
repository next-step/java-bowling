package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingFrameTest {

    @Test
    @DisplayName("프레임은 고정으로 10개 만든다")
    public void frameMaxSize10() throws Exception {
        //given
        List<BowlingFrame> frame = BowlingFrameFactory.of();

        //when
        
        //then
        assertThat(frame.size()).isEqualTo(10);
    }
}
