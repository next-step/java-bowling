package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

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

    @DisplayName("FrameScore count 테스트")
    @Test
    public void countFrameScoreTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(1, 2));
        assertTrue(frameScore.isCount(2));
    }

    @DisplayName("FrameScore strike 테스트")
    @Test
    public void checkStrikeTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(10));
        assertThat(frameScore.getResult()).isEqualTo(FrameScoreResult.STRIKE);

        FrameScore frameScore2 = FrameScore.newInstance(Arrays.asList(3, 7));
        assertThat(frameScore2.getResult()).isNotEqualTo(FrameScoreResult.STRIKE);
    }

    @DisplayName("FrameScore spare 테스트")
    @Test
    public void checkSpareTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(10));
        assertThat(frameScore.getResult()).isNotEqualTo(FrameScoreResult.SPARE);

        FrameScore frameScore2 = FrameScore.newInstance(Arrays.asList(3, 7));
        assertThat(frameScore2.getResult()).isEqualTo(FrameScoreResult.SPARE);
    }

}
