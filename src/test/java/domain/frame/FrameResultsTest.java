package domain.frame;

import domain.Score;
import domain.state.closed.Strike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static domain.state.closed.Strike.STRIKE_SYMBOL;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultsTest {

    @Test
    void 각_프레임의_상태를_반환한다() {
        //given
        FrameResult testFrame1 = FrameResult.of(new Strike(), Score.of(10, 0));
        FrameResult testFrame2 = FrameResult.of(new Strike(), Score.of(10, 0));
        FrameResult testFrame3 = FrameResult.of(new Strike(), Score.of(10, 0));
        List<FrameResult> testResults = Arrays.asList(testFrame1, testFrame2, testFrame3);

        //when
        FrameResults frameResults = FrameResults.from(testResults);

        //then
        assertThat(frameResults.getFrameResults()).extracting(FrameResult::getState)
                .containsExactly(STRIKE_SYMBOL, STRIKE_SYMBOL, STRIKE_SYMBOL);
    }

    @Test
    void 각_프레임의_최종점수를_반환한다() {
        //given
        FrameResult testFrame1 = FrameResult.of(new Strike(), Score.of(10, 0));
        FrameResult testFrame2 = FrameResult.of(new Strike(), Score.of(10, 0));
        FrameResult testFrame3 = FrameResult.of(new Strike(), Score.of(10, 0));
        List<FrameResult> testResults = Arrays.asList(testFrame1, testFrame2, testFrame3);

        //when
        FrameResults frameResults = FrameResults.from(testResults);

        //then
        assertThat(frameResults.getFrameResults()).extracting(FrameResult::getScore)
                .containsExactly(10, 20, 30);
    }


}
