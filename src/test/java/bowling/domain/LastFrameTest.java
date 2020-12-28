package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = Frame.first()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame();
    }

    @DisplayName("10 번째 프래임은 LastFrame 이다")
    @Test
    void should_be_LastFrame(){
        Frame last = Frame.first()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame()
                .nextFrame();

        assertThat(last instanceof LastFrame).isTrue();
    }

    @DisplayName("마지막 프레임의 다음 프레임은 null 을 return 한다")
    @Test
    void nextIsNull(){
        assertThat(frame.nextFrame()).isNull();
    }

}