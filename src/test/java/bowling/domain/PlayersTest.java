package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


class PlayersTest {

    private static final String PLAYER1 = "JTH";
    private static final String PLAYER2 = "POB";
    private static final String PLAYER3 = "PJS";

    @Test
    void of() {
        Players players = Players.of(Arrays.asList(PLAYER1, PLAYER2, PLAYER3));
        assertThat(players.getPlayers())
                .anyMatch(v -> PLAYER1.equals(v.name()))
                .anyMatch(v -> PLAYER2.equals(v.name()))
                .anyMatch(v -> PLAYER3.equals(v.name()));
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Players.of(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.of(Arrays.asList(PLAYER1, PLAYER1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getCurrentPlayerName() {
        Players players = Players.of(Arrays.asList(PLAYER1, PLAYER2, PLAYER3));
        assertThat(players.getCurrentPlayerName())
                .isEqualTo(PLAYER1);

        players.shot(10);
        assertThat(players.getCurrentPlayerName())
                .isEqualTo(PLAYER2);

        players.shot(5);
        assertThat(players.getCurrentPlayerName())
                .isEqualTo(PLAYER3);

        players.shot(5);
        assertThat(players.getCurrentPlayerName())
                .isEqualTo(PLAYER2);
    }

    @Test
    void isGameSet() {
        Player player1 = spy(Player.of(PLAYER1));
        Player player2 = spy(Player.of(PLAYER2));
        Players players = Players.of(player1, player2);

        assertThat(players.isGameSet())
                .isFalse();

        when(player1.isGameSet()).thenReturn(true);
        assertThat(players.isGameSet())
                .isFalse();

        when(player2.isGameSet()).thenReturn(true);
        assertThat(players.isGameSet())
                .isTrue();
    }
}
