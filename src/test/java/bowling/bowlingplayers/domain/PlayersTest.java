package bowling.bowlingplayers.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayersTest {

    @Test
    @DisplayName("Players 생성")
    void create() {
        // given
        List<String> nameString = Arrays.asList("SYH", "JMK");
        // when
        Players players = new Players(nameString);
        // then
        assertThat(players).isEqualTo(new Players(nameString));
    }

    @Test
    @DisplayName("pitchingPlayer 반환")
    void pitchingPlayer() {
        // given
        Players players = new Players(Arrays.asList("SYH", "JMK"));
        // when
        Player player = players.pitchingPlayer();
        // then
        assertThat(player).isEqualTo(new Player("SYH"));
    }

    @Test
    @DisplayName("pitch 후 pitchingPlayer 반환")
    void pitchingPlayer_() {
        // given
        Players players = new Players(Arrays.asList("SYH", "JMK"));
        // when
        Player player1 = players.pitchingPlayer();
        player1.pitch(10);
        Player player2 = players.pitchingPlayer();
        player2.pitch(10);
        Player player3 = players.pitchingPlayer();
        player3.pitch(10);
        Player player4 = players.pitchingPlayer();
        player4.pitch(10);
        // then
        assertThat(player3).isEqualTo(player1);
        assertThat(player4).isEqualTo(player2);
    }
}