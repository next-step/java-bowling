package bowling.domain;

import bowling.InvalidFrameIndexException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FrameTest {
    @DisplayName("첫 번째 Frame 생성")
    @Test
    void create_first() {
        // when
        final Frame frame = Frame.createFirst();

        // then
        assertThat(frame).isNotNull();
    }

    @DisplayName("다음 Frame 생성")
    @Test
    void create_next() {
        // given
        final Frame firstFrame = Frame.createFirst();

        // when
        final Frame nextFrame = firstFrame.createNext();

        // then
        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("마지막 Frame 생성")
    @Test
    void create_final_frame() {
        // given
        final Frame beforeFinal = NormalFrame.of(8);

        // when
        final Frame finalFrame = beforeFinal.createNext();

        // then
        assertThat(finalFrame).isNotNull();
    }

    @DisplayName("마지막 Frame이 createNext 를 호출하는 경우 예외 throw")
    @Test
    void throw_exception_when_final_frame_call_createNext() {
        // given
        final Frame finalFrame = FrameFactoryTest.FINAL;

        // when 
        final Throwable thrown = catchThrowable(finalFrame::createNext);

        // then
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(InvalidFrameIndexException.class);
    }
    
    @DisplayName("마지막 Frame은 next Frame을 가지고 있지 않음")
    @Test
    void next_test() {
        final Frame finalFrame = FrameFactoryTest.FINAL;
        
        // when
        final Optional<Frame> frame = finalFrame.next();
        
        // then
        assertThat(frame.isPresent()).isFalse();
    }
}
