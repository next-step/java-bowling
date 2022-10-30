package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {

    @Test
    @DisplayName("STRIKE Enum 조회 테스트")
    void fromTest1() {
        Scores scores = new Scores();
        scores.record(10);
        assertThat(scores.isStrike()).isTrue();
        assertThat(FrameScore.from(scores)).isEqualTo(FrameScore.STRIKE);
    }

    @Test
    @DisplayName("SPARE Enum 조회 테스트")
    void fromTest2() {
        Scores scores = new Scores();
        scores.record(5);
        scores.record(5);
        assertThat(scores.isSpare()).isTrue();
        assertThat(FrameScore.from(scores)).isEqualTo(FrameScore.SPARE);
    }

    @Test
    @DisplayName("ONGOING Enum 조회 테스트")
    void fromTest3() {
        Scores scores = new Scores();
        scores.record(5);
        assertThat(scores.tryOver()).isFalse();
        assertThat(FrameScore.from(scores)).isEqualTo(FrameScore.ONGOING);
    }

    @Test
    @DisplayName("FINISH Enum 조회 테스트")
    void fromTest4() {
        Scores scores = new Scores();
        scores.record(5);
        scores.record(4);
        assertThat(scores.tryOver()).isTrue();
        assertThat(FrameScore.from(scores)).isEqualTo(FrameScore.FINISH);
    }

}