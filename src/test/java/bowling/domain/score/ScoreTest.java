package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @DisplayName("일반 점수 생성 테스트")
    @Test
    void createDefaultFrameScoreTest() {
        assertThatCode(() -> Score.defaultScore(new Scores(), 10));
    }

    @DisplayName("마지막 점수 생성 테스트")
    @Test
    void createLastFrameScoreTest() {
        assertThatCode(() -> Score.lastScore(new Scores(), 10));
    }

    @DisplayName("점수 생성 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void createScoreFailedTest(int pin) {
        assertThatIllegalArgumentException().isThrownBy(() -> Score.defaultScore(new Scores(), pin));
    }

    @Nested
    @DisplayName("일반 프레임 테스트")
    class defaultFrame {

        @DisplayName("STRIKE 생성 테스트")
        @Test
        void createStrikeTest() {
            Score strike = Score.defaultScore(new Scores(), 10);
            assertThat(strike.isEqualKind(ScoreKind.STRIKE)).isTrue();
        }

        @DisplayName("SPARE 생성 테스트")
        @Test
        void createSpareTest() {
            Scores scores = new Scores();
            scores.add(Score.defaultScore(new Scores(), 5));
            Score spare = Score.defaultScore(scores, 5);
            assertThat(spare.isEqualKind(ScoreKind.SPARE)).isTrue();
        }

        @DisplayName("GUTTER 생성 테스트")
        @Test
        void createGutterTest() {
            Score gutter = Score.defaultScore(new Scores(), 0);
            assertThat(gutter.isEqualKind(ScoreKind.GUTTER)).isTrue();
        }

        @DisplayName("MISS 생성 테스트")
        @Test
        void createMissTest() {
            Score miss = Score.defaultScore(new Scores(), 5);
            assertThat(miss.isEqualKind(ScoreKind.MISS)).isTrue();
        }
    }

    @Nested
    @DisplayName("마지막 프레임 테스트")
    class lastFrame {

        @DisplayName("STRIKE 생성 테스트")
        @Test
        void createStrikeTest() {
            Scores scores = new Scores();
            scores.add(Score.lastScore(scores, 10));
            scores.add(Score.lastScore(scores, 10));
            scores.add(Score.lastScore(scores, 10));
            assertAll(
                    () -> {
                        for (Score score : scores.getScores()) {
                            assertThat(score.isEqualKind(ScoreKind.STRIKE)).isTrue();
                        }
                    }
            );
        }

        @DisplayName("SPARE 생성 테스트")
        @Test
        void createSpareTest() {
            Scores scores = new Scores();
            scores.add(Score.lastScore(new Scores(), 5));
            Score spare = Score.lastScore(scores, 5);
            assertThat(spare.isEqualKind(ScoreKind.SPARE)).isTrue();
        }

        @DisplayName("GUTTER 생성 테스트")
        @Test
        void createGutterTest() {
            Score gutter = Score.lastScore(new Scores(), 0);
            assertThat(gutter.isEqualKind(ScoreKind.GUTTER)).isTrue();
        }

        @DisplayName("MISS 생성 테스트")
        @Test
        void createMissTest() {
            Score miss = Score.lastScore(new Scores(), 5);
            assertThat(miss.isEqualKind(ScoreKind.MISS)).isTrue();
        }
    }
}