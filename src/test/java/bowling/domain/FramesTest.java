package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("frames 생성 확인")
    public void frames() throws Exception {
        //given
        Frames frames = Frames.of(UserTest.U1);
        //when

        //then
        assertThat(frames).isNotNull();
    }


}
