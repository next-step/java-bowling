package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("pitch() 호출 시 frames, pitchings 에 추가 되는지 확인")
    void pitch() {
        // given
        Game game = new Game();
        // when
        game.pitch(9);
        // then
        assertThat(game.frames().frames().size()).isEqualTo(1);
        assertThat(game.pitchings().pitchings().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("pitch() 호출 시 frames, currentPitching 추가 확인")
    void pitch1() {
        // given
        Game game = new Game();
        // when
        game.pitch(9);
        // then
        assertThat(game.frames().frames().size()).isEqualTo(1);
        assertThat(game.currentPitching()).isEqualTo(Pitching.of(9));
    }
}