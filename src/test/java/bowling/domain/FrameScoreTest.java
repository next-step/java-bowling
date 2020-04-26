package bowling.domain;

import bowling.domain.scoreType.ScoreType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FrameScoreTest {

    @Test
    void of() {
        assertThatCode(() -> FrameScore.of(Score.of(5), ScoreType.MISS_SECOND))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> FrameScore.of(Score.of(5), ScoreType.MISS_FIRST))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        FrameScore frameScore = FrameScore.of(Score.of(5), ScoreType.MISS_SECOND);
        assertThat(frameScore.getScore())
                .isEqualTo(5);
    }

    @Test
    void getScoreException() {
        FrameScore frameScore = FrameScore.of(Score.of(10), ScoreType.SPARE);
        assertThatThrownBy(frameScore::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isScoreCalculated() {
        FrameScore frameScore = FrameScore.of(Score.of(10), ScoreType.SPARE);
        assertThat(frameScore.isScoreCalculated())
                .isFalse();

        frameScore = FrameScore.of(Score.of(6), ScoreType.MISS_SECOND);
        assertThat(frameScore.isScoreCalculated())
                .isTrue();
    }

    @Test
    void addBonus() {
        FrameScore frameScore = FrameScore.of(Score.of(10), ScoreType.SPARE);
        assertThat(frameScore.isScoreCalculated())
                .isFalse();

        frameScore.addBonus(4);
        assertThat(frameScore.isScoreCalculated())
                .isTrue();
        assertThat(frameScore.getScore())
                .isEqualTo(14);
    }

    @Test
    void addBonusException() {
        FrameScore frameScore = FrameScore.of(Score.of(5), ScoreType.MISS_SECOND);
        assertThatThrownBy(() -> frameScore.addBonus(4))
                .isInstanceOf(IllegalStateException.class);
    }
}
