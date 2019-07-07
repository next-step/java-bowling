package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players();
        players.addPlayer(new Player("Jack"));
    }

    @Test
    void getPlayerName() {
        assertThat(players.getPlayerName(0)).isEqualTo("Jack");
    }

}
