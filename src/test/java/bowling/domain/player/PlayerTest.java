package bowling.domain.player;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("Player는 이름과 Frame 목록을 가지고 생성된다.")
    void create() {
        // given
        final PlayerName playerName = new PlayerName("KSD");
        final Frames frames = Frames.initialize();

        // when
        final Player player = new Player(playerName, frames);

        // then
        assertThat(player).isEqualTo(new Player(playerName, frames));
    }
}
