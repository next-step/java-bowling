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
    @DisplayName("pitch 시 다음 사람으로 넘어가는지 테스트")
    void pitch() {
        // given
        List<String> nameString = Arrays.asList("SYH", "JMK");
        Players players = new Players(nameString);
        players.pitch(10);
        // when
        players.pitch(10);
        Player checkPlayer = new Player("JMK");
        checkPlayer.pitch(10);
        // then
        assertThat(players.players().get(1)).isEqualTo(checkPlayer);
    }
}