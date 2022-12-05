package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("플레이어 추가하기")
    void test1() {
        Players players = new Players();
        players.addPlayer(new Player("abc"));
        players.addPlayer(new Player("aaa"));
        players.addPlayer(new Player("ccc"));
        assertThat(players.getPlayers()).hasSize(3);
    }

    @Test
    @DisplayName("다음에 수행할 플레이어의 이름")
    void test2() {
        Players players = new Players();
        players.addPlayer(new Player("abc"));
        players.addPlayer(new Player("aaa"));
        players.addPlayer(new Player("ccc"));

        assertThat(players.getNextPlayerName()).isEqualTo("abc");
        players.bowl(new Pin(10));
        assertThat(players.getNextPlayerName()).isEqualTo("aaa");
        players.bowl(new Pin(10));
        assertThat(players.getNextPlayerName()).isEqualTo("ccc");
    }

}
