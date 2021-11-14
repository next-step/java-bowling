package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import java.util.LinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameResultsTest {

    @Test
    @DisplayName("first frame을 받아서 next frame이 있을 때까지 FrameResults를 생성할 수 있다.")
    void createFrameResultsByFirstFrameWhenNextFrameNotNullTest() {

        // given
        Frame thirdFrame = NormalFrame.from(3, null, new Strike(Pin.of(10)));
        Frame secondFrame = NormalFrame.from(2, thirdFrame, new Strike(Pin.of(10)));
        Frame firstframe = NormalFrame.from(1, secondFrame, new Strike(Pin.of(10)));

        LinkedList<FrameResult> frameResultList = new LinkedList<>();
        frameResultList.add(FrameResult.ofScoreAndDesc(30, "X"));
        frameResultList.add(FrameResult.ofScoreAndDesc(-1, "X"));
        frameResultList.add(FrameResult.ofScoreAndDesc(-1, "X"));
        FrameResults expected = FrameResults.of(frameResultList);

        // when
        FrameResults result = FrameResults.createFrameResultsByFirstFrame(firstframe);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("first frame을 받아서 마지막 frame이 있을 때까지 FrameResults를 생성할 수 있다.")
    void createFrameResultsByFirstFrameWhenFinalFrameTest() {

        // given
        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(Pin.of(10)));
        states.add(new Strike(Pin.of(10)));
        Frame finalFrame = FinalFrame.from(10, new Strike(Pin.of(10)), states);
        Frame secondFrame = NormalFrame.from(9, finalFrame, new Strike(Pin.of(10)));
        Frame firstframe = NormalFrame.from(8, secondFrame, new Strike(Pin.of(10)));

        LinkedList<FrameResult> frameResultList = new LinkedList<>();
        frameResultList.add(FrameResult.ofScoreAndDesc(30, "X"));
        frameResultList.add(FrameResult.ofScoreAndDesc(30, "X"));
        frameResultList.add(FrameResult.ofScoreAndDesc(30, "X|X|X"));
        FrameResults expected = FrameResults.of(frameResultList);

        // when
        FrameResults result = FrameResults.createFrameResultsByFirstFrame(firstframe);

        // then
        assertThat(result).isEqualTo(expected);
    }

}