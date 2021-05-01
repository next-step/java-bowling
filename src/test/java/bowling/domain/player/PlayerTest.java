package bowling.domain.player;

import bowling.domain.TestFixture;
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
    @DisplayName("게임을 수행하지 않은 Player의 Normal Round의 종료여부는 false 이다.")
    void isEndedNotPlayed() {
        // given
        final Player player = new Player(playerName);

        // when
        final boolean ended = player.isEnded(new RoundNumber(1));

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("스트라이크를 친 Player의 Normal Round의 종료여부는 true 이다.")
    void isEndedPlayed() {
        // given
        final Player player = new Player(playerName);
        final RoundNumber firstRoundNumber = new RoundNumber(1);
        player.knockDownPin(firstRoundNumber, TestFixture.STRIKE_PIN);

        // when
        final boolean ended = player.isEnded(firstRoundNumber);

        // then
        assertThat(ended).isTrue();
    }

    @Test
    @DisplayName("일반적인 공을 던진 Player의 Normal Round의 종료여부는 false 이다.")
    void notEndedPlayed() {
        // given
        final Player player = new Player(playerName);
        final RoundNumber roundNumber = new RoundNumber(1);
        player.knockDownPin(roundNumber, new Pin(5));

        // when
        final boolean ended = player.isEnded(roundNumber);

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("거터를 한 번 던진 Player의 Normal Round의 종료여부는 false 이다.")
    void notEndedGutterOnce() {
        // given
        final Player player = new Player(playerName);
        final RoundNumber roundNumber = new RoundNumber(1);
        player.knockDownPin(roundNumber, TestFixture.GUTTER_PIN);

        // when
        final boolean ended = player.isEnded(roundNumber);

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("거터를 두 번 던진 Player의 Normal Round의 종료여부는 true 이다.")
    void notEndedGutterTwice() {
        // given
        final Player player = new Player(playerName);
        final RoundNumber roundNumber = new RoundNumber(1);
        player.knockDownPin(roundNumber, TestFixture.GUTTER_PIN);
        player.knockDownPin(roundNumber, TestFixture.GUTTER_PIN);

        // when
        final boolean ended = player.isEnded(roundNumber);

        // then
        assertThat(ended).isTrue();
    }
}
