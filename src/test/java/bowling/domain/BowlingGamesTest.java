package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    @Test
    @DisplayName("참가자 인원만큼 BowlingGame 이 생성되어야 한다.")
    void create() {
        List<Player> players = List.of(new Player("tom"), new Player("tim"));
        BowlingGames bowlingGames = BowlingGames.initialize(players);

        assertThat(bowlingGames.values().size()).isEqualTo(2);
    }
}