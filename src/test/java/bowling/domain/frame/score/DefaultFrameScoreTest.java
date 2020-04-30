package bowling.domain.frame.score;

import bowling.domain.shot.type.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DefaultFrameScoreTest {

    @Test
    void of() {
        assertThatCode(() -> DefaultFrameScore.of(5, ScoreType.MISS_SECOND))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> DefaultFrameScore.of(5, ScoreType.MISS_FIRST))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(5, ScoreType.MISS_SECOND);
        assertThat(frameScore.getScore())
                .isEqualTo(5);
    }

    @Test
    void getScoreException() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, ScoreType.SPARE);
        assertThatThrownBy(frameScore::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isScoreCalculated() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, ScoreType.SPARE);
        assertThat(frameScore.isCalculated())
                .isFalse();

        frameScore = DefaultFrameScore.of(6, ScoreType.MISS_SECOND);
        assertThat(frameScore.isCalculated())
                .isTrue();
    }

    @Test
    void addBonus() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, ScoreType.SPARE);
        assertThat(frameScore.isCalculated())
                .isFalse();

        frameScore.addBonus(4);
        assertThat(frameScore.isCalculated())
                .isTrue();
        assertThat(frameScore.getScore())
                .isEqualTo(14);
    }

    @Test
    void addBonusException() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(5, ScoreType.MISS_SECOND);
        assertThatThrownBy(() -> frameScore.addBonus(4))
                .isInstanceOf(IllegalStateException.class);
    }
}
