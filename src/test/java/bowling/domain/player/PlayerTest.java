package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
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

    @Test
    @DisplayName("Player는 해당 라운드가 끝났는지 확인할 수 있다.")
    void isEnded() {
        // given
        final Player player = new Player(playerName);

        // when
        final boolean ended = player.isEnded(new RoundNumber(1));

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("Player는 해당 라운드의 Pin을 쓰러뜨릴 수 있다.")
    void knockDownPin() {
        // given
        final Player player = new Player(playerName);
        final RoundNumber roundNumber = new RoundNumber(1);
        final Pin strikePin = new Pin();

        // when
        player.knockDownPin(roundNumber, strikePin);

        // then
        assertThat(player.isEnded(roundNumber)).isTrue();
    }
}
