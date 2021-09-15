package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("pitch() 호출 시 frames, currentPitching 추가 확인")
    void pitch1() {
        // given
        Game game = new Game();
        // when
        game.pitch(9);
        Game checkGame = new Game();
        checkGame.pitch(9);

        // then
        assertThat(game).isEqualTo(checkGame);
    }
}