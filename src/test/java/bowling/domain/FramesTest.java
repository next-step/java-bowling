package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FrameNumber.FRAME_LAST_NUMBER;
import static bowling.domain.FrameNumber.FRAME_START_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("Frames init() 생성 후 객체 확인")
    public void frames_initFramesSize_isEqualTo() {
        //given
        Frames frames = Frames.init();

        //when
        List<Frame> expect = IntStream.rangeClosed(FRAME_START_NUMBER, (FRAME_LAST_NUMBER - 1))
                .mapToObj(FrameNumber::from)
                .map(NormalFrame::from)
                .collect(Collectors.toList());
        expect.add(FinalFrame.from());

        //then
        assertThat(frames).isEqualTo(Frames.from(expect));
    }

    @Test
    @DisplayName("Frames pitch() 10점 투구 후 다음 Frame 으로 넘어갔는지 확인")
    public void frames_normalPitchNext_isEqualTo() {
        //given
        Frames frames = Frames.init();

        //when
        frames.pitch(10);
        Frame actual = frames.getCurrentFrame();

        //then
        assertThat(actual).isEqualTo(NormalFrame.from(FrameNumber.from(2)));
    }
}
