package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PlayersTest {

    @DisplayName("Players 생성 테스트")
    @Test
    void playersConstructorTest(){

        // given
        String[] playerNames = {"AAA", "BBB", "CCC"};

        // when
        Players players = Players.from(playerNames);

        // then
        assertThat(players.getPlayers()).containsExactly(Player.from("AAA"), Player.from("BBB"), Player.from("CCC"));

    }

}
