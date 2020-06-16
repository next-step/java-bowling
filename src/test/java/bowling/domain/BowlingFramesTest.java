package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.BowlingFrames;
import bowling.domain.Frame;
import bowling.domain.FrameResult;
import bowling.domain.NormalFrame;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingFramesTest {

    @DisplayName("frame이 10개가 아니면 예외가 발생한다.")
    @Test
    void frame_size(){
        List<Frame> frames = Arrays.asList(NormalFrame.first());

        assertThatThrownBy(()->new BowlingFrames(frames))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("프레임들의 결과를 조회한다.")
    @Test
    void play(){
        BowlingFrames bowlingFrames = BowlingFrames.newInstance();
        bowlingFrames.play(10);
        bowlingFrames.play(1);
        bowlingFrames.play(2);

        List<FrameResult> frameResults = bowlingFrames.getResults();

        List<FrameResult> expect= Arrays.asList(
            new FrameResult(Arrays.asList(10)),
            new FrameResult(Arrays.asList(1,2))
        );
        assertThat(frameResults).isEqualTo(expect);
    }
}
