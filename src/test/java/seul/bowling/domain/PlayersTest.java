package seul.bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    void of() {
        List<String> names = Arrays.asList("PES", "SSS");

        Players players = Players.of(names);

        assertThat(players.getPlayers()).hasSize(2);
        assertThat(players.getPlayers().get(0).getName()).isEqualTo("PES");
        assertThat(players.getPlayers().get(1).getName()).isEqualTo("SSS");
    }

    @Test
    void play() {
        List<String> names = Arrays.asList("PES", "SSS");
        Players players = Players.of(names);

        players.play("PES", 10);

        assertThat(players.getPlayers().get(0).getFrames().getFrame(0).getScore()).isEqualTo(10);
    }

    @Test
    void getPlayer() {
        List<String> names = Arrays.asList("PES", "SSS");
        Players players = Players.of(names);

        Player player = players.getPlayer("PES");

        assertThat(player.getName()).isEqualTo("PES");
    }
}
