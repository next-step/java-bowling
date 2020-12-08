package bowling.domain;

import bowling.InvalidFrameIndexException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FrameTest {
    @DisplayName("첫 번째 Frame 생성")
    @Test
    void create_first() {
        // when
        final Frame frame = Frame.first();

        // then
        assertThat(frame).isNotNull();
    }

    @DisplayName("다음 Frame 생성")
    @Test
    void create_next() {
        // given
        final Frame firstFrame = Frame.first();

        // when
        final Frame nextFrame = firstFrame.next();

        // then
        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("마지막 Frame 생성")
    @Test
    void create_final_frame() {
        // given
        final Frame beforeFinal = new NormalFrame(8);

        // when
        final Frame finalFrame = beforeFinal.next();

        // then
        assertThat(finalFrame).isNotNull();
    }

    @DisplayName("마지막 Frame이 next를 호출하는 경우 예외 throw")
    @Test
    void throw_exception_when_final_frame_call_next() {
        // given
        final Frame finalFrame = new FinalFrame(9);

        // when 
        final Throwable thrown = catchThrowable(finalFrame::next);

        // then
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(InvalidFrameIndexException.class);
    }
}
