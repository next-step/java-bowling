package bowling;

import bowling.domain.frame.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    @DisplayName("Frames init test")
    void framesInitTest() {
        Frames frames = Frames.init();

        List<Frame> expectList = IntStream.range(1, 10)
                .mapToObj(FrameRound::get)
                .map(NormalFrame::get)
                .collect(Collectors.toList());
        expectList.add(FinalFrame.get());
        assertThat(frames).isEqualTo(Frames.get(expectList));
    }

    @Test
    @DisplayName("다음 라운드로 넘어가는지 테스트")
    void goNextFrameTest() {
        Frames frames = Frames.init();

        frames.pitch(10);

        assertThat(frames.getCurrentFrame()).isEqualTo(NormalFrame.get(FrameRound.get((2))));
    }
}
