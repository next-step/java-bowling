package bowling.domain;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreTest {
    @DisplayName("일반 프레임 1구 점수 생성")
    @Test
    void createDefaultFrameScore() {
        assertThatCode(() -> Score.defaultScore(new Scores(), 10));
    }

    @DisplayName("마지막 프레임 1구 점수 생성")
    @Test
    void createLastFrameScore() {
        assertThatCode(() -> Score.lastScore(new Scores(), 10));
    }

    @DisplayName("점수가 10점이 넘거나 음수일 경우 throws Exception")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void createScoreFailbyInvalidPoint(int point) {
        assertThatIllegalArgumentException().isThrownBy(() -> Score.defaultScore(new Scores(), point));
    }

    @Nested
    @DisplayName("일반 프레임")
    class defaultFrame {

        @DisplayName("스트라이크 생성")
        @Test
        void createStrike() {
            Score strike = Score.defaultScore(new Scores(), 10);

            assertThat(strike.isEqualScoreType(ScoreType.STRIKE)).isTrue();
        }

        @DisplayName("스페어 생성")
        @Test
        void createSpare() {
            Scores scores = new Scores();
            scores.add(Score.defaultScore(new Scores(), 5));

            Score spare = Score.defaultScore(scores, 5);

            assertThat(spare.isEqualScoreType(ScoreType.SPARE)).isTrue();
        }

        @DisplayName("거터 생성")
        @Test
        void createGutter() {
            Score gutter = Score.defaultScore(new Scores(), 0);

            assertThat(gutter.isEqualScoreType(ScoreType.GUTTER)).isTrue();
        }

        @DisplayName("미스 생성")
        @Test
        void createMiss() {
            Score miss = Score.defaultScore(new Scores(), 5);

            assertThat(miss.isEqualScoreType(ScoreType.MISS)).isTrue();
        }
    }

    @Nested
    @DisplayName("마지막 프레임")
    class lastFrame {

        @DisplayName("스트라이크 생성")
        @Test
        void createStrike() {
            Scores scores = new Scores();

            scores.add(Score.lastScore(scores, 10));
            scores.add(Score.lastScore(scores, 10));
            scores.add(Score.lastScore(scores, 10));

            assertAll(
                    () -> {
                        for (Score score : scores.getScores()) {
                            assertThat(score.isEqualScoreType(ScoreType.STRIKE)).isTrue();
                        }
                    }
            );
        }

        @DisplayName("스페어 생성")
        @Test
        void createSpare() {
            Scores scores = new Scores();
            scores.add(Score.lastScore(new Scores(), 5));

            Score spare = Score.lastScore(scores, 5);

            assertThat(spare.isEqualScoreType(ScoreType.SPARE)).isTrue();
        }

        @DisplayName("거터 생성")
        @Test
        void createGutter() {
            Score gutter = Score.lastScore(new Scores(), 0);

            assertThat(gutter.isEqualScoreType(ScoreType.GUTTER)).isTrue();
        }

        @DisplayName("미스 생성")
        @Test
        void createMiss() {
            Score miss = Score.lastScore(new Scores(), 5);

            assertThat(miss.isEqualScoreType(ScoreType.MISS)).isTrue();
        }
    }
}
