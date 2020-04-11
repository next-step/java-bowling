package bowling;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    private final List<FrameScore> frameScores = new ArrayList<>();

    @Before
    public void init() {
        frameScores.add(FrameScore.newInstance(Arrays.asList(5, 5)));
        frameScores.add(FrameScore.newInstance(Arrays.asList(10, 5, 5)));
        frameScores.add(FrameScore.newInstance(Collections.singletonList(10)));
        frameScores.add(FrameScore.newInstance(Arrays.asList(10, 10, 10)));
        frameScores.add(FrameScore.newInstance(Collections.singletonList(2)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(FrameScore::new);
    }

    @DisplayName("FrameScore add 테스트")
    @Test
    public void addFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        assertThatCode(() -> frameScore.add(5));
        assertThatCode(() -> frameScore.add(4));
    }

    @DisplayName("FrameScore sum 테스트")
    @Test
    public void sumFrameScoreTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(1, 2));
        assertThat(frameScore.sum()).isEqualTo(3);
    }

    @DisplayName("FrameScore 종료 테스트 - 마지막 프레임")
    @Test
    public void isOverLastFrameTest() {
        assertFalse(frameScores.get(0).isOverLastFrame());
        assertTrue(frameScores.get(1).isOverLastFrame());
        assertFalse(frameScores.get(2).isOverLastFrame());
        assertTrue(frameScores.get(3).isOverLastFrame());
    }

    @DisplayName("FrameScore 종료 테스트 - 1~9 프레임")
    @Test
    public void isOverCommonFrameTest() {
        assertTrue(frameScores.get(0).isOverCommonFrame());
        assertTrue(frameScores.get(2).isOverCommonFrame());
        assertFalse(frameScores.get(4).isOverCommonFrame());
    }


}
