package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("프레임, 턴 진행 확인")
    public void first() throws Exception {
        //given
        Frame frame = Frame.first(5);

        //when

        //then
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("프레임, 턴 종료 확인")
    public void second() throws Exception {
        //given
        Frame frame = Frame.first(5).second(3);

        //when

        //then
        assertThat(frame.isEnd()).isTrue();
    }
}
