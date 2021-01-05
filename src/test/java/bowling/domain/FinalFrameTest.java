package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = Frame.createFinal(10);
    }

    @DisplayName("10 번째 프래임은 LastFrame 이다")
    @Test
    void should_be_LastFrame(){
        Frame frame = Frame.createNormal(9);
        Frame last = frame.createNext();
        assertThat(last instanceof FinalFrame).isTrue();
    }

    @DisplayName("마지막 프레임의 다음 프레임은 null 을 return 한다")
    @Test
    void nextIsNull(){
        assertThat(frame.createNext()).isNull();
    }
}