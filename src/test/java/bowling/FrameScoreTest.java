package bowling;

import bowling.domain.FrameScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreTest {
    @Test
    @DisplayName("남은 시도 회수가 0일 때 테스트")
    void hasNoTryLeftTest() {
        FrameScore frameScore = FrameScore.of(7,0);
        assertThat(frameScore.hasNoTryLeft()).isTrue();
        assertThat(frameScore.hasOneTryLeft()).isFalse();
        assertThat(frameScore.hasTwoTriesLeft()).isFalse();
    }

    @Test
    @DisplayName("남은 시도 회수가 1일 때 테스트")
    void hasOneTryLeftTest() {
        FrameScore frameScore = FrameScore.of(10,1);
        assertThat(frameScore.hasNoTryLeft()).isFalse();
        assertThat(frameScore.hasOneTryLeft()).isTrue();
        assertThat(frameScore.hasTwoTriesLeft()).isFalse();
    }

    @Test
    @DisplayName("남은 시도 회수가 2일 때 테스트")
    void hasTwoTriesLeftTest() {
        FrameScore frameScore = FrameScore.of(10,2);
        assertThat(frameScore.hasNoTryLeft()).isFalse();
        assertThat(frameScore.hasOneTryLeft()).isFalse();
        assertThat(frameScore.hasTwoTriesLeft()).isTrue();
    }

    @Test
    @DisplayName("점수 확인 테스트")
    void scoreTest() {
        FrameScore frameScore = FrameScore.of(7, 0);
        assertThat(frameScore.Score()).isEqualTo(7);
    }
}
