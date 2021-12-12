package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultsTest {

    public static FrameResults STRIKE_AND_FIVE = new FrameResults(
            Arrays.asList(FrameResultTest.STRIKE_RESULT, FrameResultTest.FIVE_POINT_RESULT)
    );
    public static FrameResults STRIKE = new FrameResults(
            Arrays.asList(FrameResultTest.STRIKE_RESULT, FrameResultTest.READY)
    );

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new FrameResults(Arrays.asList(FrameResultTest.FIVE_POINT_RESULT)))
                .isEqualTo(new FrameResults(Arrays.asList(FrameResultTest.FIVE_POINT_RESULT)));
    }
}
