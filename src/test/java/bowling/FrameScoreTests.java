package bowling;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    private List<FrameScore> frameScores;

    @BeforeEach
    public void init() {
        frameScores.add(FrameScore.newInstance(Arrays.asList(5, 5)));
        frameScores.add(FrameScore.newInstance(Arrays.asList(10, 5, 5)));
        frameScores.add(FrameScore.newInstance(Collections.singletonList(10)));
        frameScores.add(FrameScore.newInstance(Arrays.asList(10, 10, 10)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(FrameScore::new);
    }

}
