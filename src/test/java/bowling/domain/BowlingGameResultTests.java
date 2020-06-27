package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameResultTests {
    @DisplayName("FrameResults를 주입받아서 객체를 생성 할 수 있다.")
    @Test
    void createTest() {
        FrameResults frameResults = new FrameResults(Collections.singletonList(FrameResult.STRIKE));
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frameResults);

        assertThat(bowlingGameResult).isEqualTo(new BowlingGameResult(frameResults));
    }

    @DisplayName("FrameResults와 FrameScore를 주입받아서 객체를 생성 할 수 있다.")
    @Test
    void createRefactorTest() {
        FrameResults frameResults = new FrameResults(Collections.singletonList(FrameResult.STRIKE));
        FrameScore frameScore = new FrameScore(FrameScoreStatus.COMPLETE, 10);
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frameResults, frameScore);

        assertThat(bowlingGameResult).isEqualTo(new BowlingGameResult(frameResults, frameScore));
    }
}
