package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
        assertThatThrownBy(() -> Players.of(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.of(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.of(Arrays.asList(PLAYER1,PLAYER1)))
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

}
