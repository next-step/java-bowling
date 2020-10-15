package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGamesTest {
    @Test
    void create() {
        BowlingGames bowlingGames = new BowlingGames(Arrays.asList("PJS", "KYJ"));

        assertThat(bowlingGames).isEqualTo(new BowlingGames(Arrays.asList("PJS", "KYJ")));
    }

    @Test
    void pitch() {
        BowlingGames bowlingGames = new BowlingGames(Arrays.asList("PJS"));
        Player player = new Player("PJS");

        bowlingGames.pitch(player, 10);

        assertThat(bowlingGames.getFrames(player).size()).isEqualTo(1);
    }
}
