package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrameScoreResultTests {

    @DisplayName("FrameScoreResult strike 테스트")
    @Test
    public void checkStrikeTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(null, Score.of(10));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.STRIKE);

        assertThat(frameScoreResult.calculateTotalScore(TotalScore.of(10), NextAddingUpScores.newInstance(6, 4))).isEqualTo(TotalScore.of(20));
        assertTrue(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(6, 4)));
        assertFalse(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(6)));
    }

    @DisplayName("FrameScoreResult spare 테스트")
    @Test
    public void checkSpareTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(3), Score.of(7));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.SPARE);

        assertThat(frameScoreResult.calculateTotalScore(TotalScore.of(10), NextAddingUpScores.newInstance(6, 4))).isEqualTo(TotalScore.of(16));
        assertTrue(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(5)));
        assertFalse(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(Collections.EMPTY_LIST)));
    }

    @DisplayName("FrameScoreResult miss 테스트")
    @Test
    public void checkMissTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(3), Score.of(5));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.MISS);

        assertThat(frameScoreResult.calculateTotalScore(TotalScore.of(8), NextAddingUpScores.newInstance(6, 4))).isEqualTo(TotalScore.of(8));
        assertTrue(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(5, 6)));
    }

    @DisplayName("FrameScoreResult gutter 테스트")
    @Test
    public void checkGutterTest() {
        FrameScoreResult frameScoreResult = FrameScoreResult.of(Score.of(0), Score.of(0));
        assertThat(frameScoreResult).isEqualTo(FrameScoreResult.GUTTER);

        assertThat(frameScoreResult.calculateTotalScore(TotalScore.of(0), NextAddingUpScores.newInstance(6, 4))).isEqualTo(TotalScore.of(0));
        assertTrue(frameScoreResult.canCalculateTotalScore(NextAddingUpScores.newInstance(5, 6)));
    }

}
