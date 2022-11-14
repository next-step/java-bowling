package bowling.step2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BowlingGameTest {
    @Test
    void 게임점수_기록이_잘되었는지() {
        BowlingGame bowlingGame = new BowlingGame("JK");
        bowlingGame.recode(10);
        int frameNum = bowlingGame.player().currentFrameNum();
        assertSoftly(softly -> {
            assertThat(bowlingGame.player().scoreMap().get(frameNum).isContainingStrike()).isTrue();
            assertThat(bowlingGame.IsEndOfOneFrame(frameNum)).isTrue();
        });

        BowlingGame bowlingGame2 = new BowlingGame("MIN");
        bowlingGame2.recode(2);
        int frameNum2 = bowlingGame2.player().currentFrameNum();
        assertSoftly(softly -> {
            assertThat(bowlingGame2.player().scoreMap().get(frameNum2).Scores().get(0).score()).isEqualTo(2);
            assertThat(bowlingGame2.IsEndOfOneFrame(frameNum2)).isFalse();
        });
    }

}