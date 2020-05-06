package bowling.domain;

import bowling.domain.frame.MockFrame;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayersTest {

    private static final Player PLAYER1 = Player.ofName("JTH");
    private static final Player PLAYER2 = Player.ofName("POB");
    private static final Player PLAYER3 = Player.ofName("PJS");

    @Test
    void of() {
        Players players = Players.ofNames(PLAYER1, PLAYER2, PLAYER3);
        assertThat(players.getPlayers())
                .anyMatch(PLAYER1::equals)
                .anyMatch(PLAYER2::equals)
                .anyMatch(PLAYER3::equals);
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Players.ofNames(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.ofNames(Arrays.asList(PLAYER1.name(), PLAYER1.name())))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getCurrentPlayer() {
        Players players = Players.ofNames(PLAYER1, PLAYER2, PLAYER3);
        assertThat(players.getCurrentPlayer())
                .isEqualTo(PLAYER1);

        players.getCurrentPlayer().shot(10);
        assertThat(players.getCurrentPlayer())
                .isEqualTo(PLAYER2);

        players.getCurrentPlayer().shot(5);
        assertThat(players.getCurrentPlayer())
                .isEqualTo(PLAYER3);

        players.getCurrentPlayer().shot(5);
        assertThat(players.getCurrentPlayer())
                .isEqualTo(PLAYER2);
    }

    @Test
    void isGameSet() {
        Player player1 = new Player("tt1", new Frames(new MockFrame(10, true)));
        Player player2 = new Player("tt1", new Frames(new MockFrame(10, false)));
        Players players = Players.ofNames(player1, player2);

        assertThat(players.isGameSet())
                .isFalse();

        players = Players.ofNames(player1);

        assertThat(players.isGameSet())
                .isTrue();
    }
}
