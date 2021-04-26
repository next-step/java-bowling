package bowling.domain.player;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    PlayerName playerName;

    @BeforeEach
    void setUp() {
        playerName = PlayerName.valueOf("KSD");
    }

    @Test
    @DisplayName("Player는 이름과 Frame 목록을 가지고 생성된다.")
    void create() {
        // given
        final Frames frames = Frames.initialize();

        // when
        final Player player = new Player(playerName, frames);

        // then
        assertThat(player).isEqualTo(new Player(playerName, frames));
    }

    @Test
    @DisplayName("Player는 이름만 가지고 빈 목록과 함께 생성된다.")
    void createWithPlayerName() {
        // given
        // when
        final Player player = new Player(playerName);

        // then
        assertThat(player).isEqualTo(new Player(playerName, Frames.initialize()));
    }
}
