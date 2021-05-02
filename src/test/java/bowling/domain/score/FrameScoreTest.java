package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameScoreTest {
    @Test
    @DisplayName("노멀 프레임, 스페어 처리 테스트")
    void spareTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(3);
        frameScore.addScore(7);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("3", "/"));
    }

    @Test
    @DisplayName("노멀 프레임, 스트라이크 테스트")
    void strikeTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(10);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("X"));
    }

    @Test
    @DisplayName("노멀 프레임, 거터, 스페어 테스트")
    void missAfterStrikeTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(0);
        frameScore.addScore(10);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("-", "/"));
    }

    @Test
    @DisplayName("노멀 프레임, 거터, 일반 점수 테스트")
    void missNormalScoreTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(0);
        frameScore.addScore(8);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("-", "8"));
    }

    @Test
    @DisplayName("노멀 프레임, 일반 점수 테스트")
    void normalScoreTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(4);
        frameScore.addScore(3);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("4", "3"));
    }

    @Test
    @DisplayName("노멀 프레임, 거터 거터 테스트")
    void normalMissMissTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(0);
        frameScore.addScore(0);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("-", "-"));
    }

    @Test
    @DisplayName("파이널 프레임, 스트라이크 스크라이크 스트라이크 테스트")
    void finalStrikeStrikeStrikeTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(10);
        frameScore.addScore(10);
        frameScore.addScore(10);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("X", "X", "X"));
    }

    @Test
    @DisplayName("파이널 프레임, 스트라이크 스페어 테스트")
    void finalStrikeSpareTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(10);
        frameScore.addScore(3);
        frameScore.addScore(7);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("X", "3", "/"));
    }

    @Test
    @DisplayName("파이널 프레임, 스트라이크 일반 득점 테스트")
    void finalStrikeNonSpareTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(10);
        frameScore.addScore(3);
        frameScore.addScore(6);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("X", "3", "6"));
    }

    @Test
    @DisplayName("파이널 프레임, 스페어 스트라이크 테스트")
    void finalSpareStrikeTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore(3);
        frameScore.addScore(7);
        frameScore.addScore(10);

        assertThat(frameScore.getFormattedScores()).isEqualTo(Arrays.asList("3", "/", "X"));
    }
}
