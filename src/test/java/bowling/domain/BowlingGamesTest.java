package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private BowlingGames bowlingGames;

    @BeforeEach
    void setup() {
        List<Player> players = List.of(new Player("tom"), new Player("tim"));
        bowlingGames = BowlingGames.initialize(players);
    }

    @Test
    @DisplayName("참가자 인원만큼 BowlingGame 이 생성되어야 한다.")
    void create() {
        List<Player> players = List.of(new Player("tom"), new Player("tim"));
        BowlingGames bowlingGames = BowlingGames.initialize(players);

        assertThat(bowlingGames.values().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("해당 프레임이 종료되지 않으면 동일한 선수가 투구한다.")
    void samePlayer() {
        Player player = bowlingGames.currentPlayer();
        bowlingGames.bowl(5, player);

        assertThat(player).isEqualTo(new Player("tom"));
        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("tom"));
    }

    @Test
    @DisplayName("해당 프레임이 종료되면 다른 선수가 투구한다.")
    void otherPlayer() {
        Player player = bowlingGames.currentPlayer();
        bowlingGames.bowl(10, player);

        assertThat(player).isEqualTo(new Player("tom"));
        assertThat(bowlingGames.currentPlayer()).isEqualTo(new Player("tim"));
    }

    @Test
    @DisplayName("모든 프레임이 종료되면 게임도 종료된다.")
    void isFinish() {
        for (int i = 0; i < 24; i++) {
            Player player = bowlingGames.currentPlayer();
            bowlingGames.bowl(10, player);
        }

        assertThat(bowlingGames.isFinish()).isTrue();
    }
}