package bowling.domain.result;

import bowling.domain.result.GameResult;
import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class GameResultTest {
    @DisplayName("PlayerName 과 생성된 10 Frames를 인자로 주면, GameResult 객체가 생성된다.")
    @Test
    void createGameResult() {
        //given
        PlayerName playerName = PlayerName.of("SSJ");
        Frames frames = Frame.createTenFrames();

        //when, then
        assertThatCode(() -> {
            new GameResult(playerName, frames);
        }).doesNotThrowAnyException();
    }
}