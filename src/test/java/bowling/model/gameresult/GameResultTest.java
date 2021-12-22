package bowling.model.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    @Test
    @DisplayName("frameNo로 마지막 프레임 구분 테스트")
    void isFinalFrameTest() {
        assertTrue(new GameResult(10,"TEST").isFinalResult());
        assertFalse(new GameResult(1,"TEST").isFinalResult());
    }

    @Test
    @DisplayName("set 후 해당 frameNo에 맞는 객체 바뀌는 것 테스트")
    void setTest() {
        GameResults gameResults = new GameResults();
        GameResult gameResult = new GameResult(5, "TEST");
        assertThat(gameResults.getGameResults().get(4)).isNotEqualTo(gameResult);
        gameResults.set(gameResult);
        assertThat(gameResults.getGameResults().get(4)).isEqualTo(gameResult);
    }
}
