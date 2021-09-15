package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreFramesTest {
    private final ScoreFrames scoreFrames = new ScoreFrames();

    @Test
    @DisplayName("게임이 진행중인지 체크 - 12회 스트라이크 이후 게임 종료")
    void isContinuedTest() {
        boolean beforeBowl = scoreFrames.isContinued();

        for (int i = 0; i < 12; i++) {
            scoreFrames.bowl(10);
        }

        boolean afterBowl = scoreFrames.isContinued();

        assertThat(beforeBowl).isTrue();
        assertThat(afterBowl).isFalse();
    }
}
