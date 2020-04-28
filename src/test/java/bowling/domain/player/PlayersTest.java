package bowling.domain.player;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayersTest {

    @Test
    public void addPlayer_success() throws Exception {
        //given
        Player player1 = new Player("aaa");
        Player player2 = new Player("bbb");
        final Players compare = new Players(Arrays.asList(player1, player2));
        Players players = new Players();

        //when
        players = players.addPlayer(player1);
        players = players.addPlayer(player2);

        //then
        assertTrue(players.equals(compare));
    }
}
