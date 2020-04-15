package bowling.domain.result;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

public class GameResultsTest {
    @DisplayName("GameResult 목록을 주면 GameResults 객체를 생성된다.")
    @Test
    void create() {
        //given
        GameResult gameResult1 = new GameResult(PlayerName.of("SSJ"), Frame.createTenFrames());
        GameResult gameResult2 = new GameResult(PlayerName.of("YSH"), Frame.createTenFrames());

        //when, then
        assertThatCode(() -> {
            new GameResults(Arrays.asList(gameResult1, gameResult2));
        }).doesNotThrowAnyException();
    }
}