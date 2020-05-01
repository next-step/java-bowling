package bowling.domain.frame.score;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DefaultFramePinsTest {

    @Test
    void of() {
        assertThatCode(() -> DefaultFrameScore.of(5, 0))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> DefaultFrameScore.of(5, -1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> DefaultFrameScore.of(-1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(5, 0);
        assertThat(frameScore.getScore())
                .isEqualTo(5);
    }

    @Test
    void getScoreException() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, 1);
        assertThatThrownBy(frameScore::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isScoreCalculated() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, 1);
        assertThat(frameScore.isCalculated())
                .isFalse();

        frameScore = DefaultFrameScore.of(6, 0);
        assertThat(frameScore.isCalculated())
                .isTrue();
    }

    @Test
    void addBonus() {
        DefaultFrameScore frameScore = DefaultFrameScore.of(10, 1);
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
        DefaultFrameScore frameScore = DefaultFrameScore.of(5, 0);
        assertThatThrownBy(() -> frameScore.addBonus(4))
                .isInstanceOf(IllegalStateException.class);
    }
}
