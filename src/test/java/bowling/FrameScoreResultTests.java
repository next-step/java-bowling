package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreResultTests {

    @DisplayName("FrameScoreResult strike 테스트")
    @Test
    public void checkStrikeTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(null, Score.of(10));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.STRIKE);
    }

    @DisplayName("FrameScoreResult spare 테스트")
    @Test
    public void checkSpareTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(3), Score.of(7));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.SPARE);
    }

    @DisplayName("FrameScoreResult miss 테스트")
    @Test
    public void checkMissTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(3), Score.of(5));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.MISS);
    }

    @DisplayName("FrameScoreResult gutter 테스트")
    @Test
    public void checkGutterTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(0), Score.of(0));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.GUTTER);
    }

}
