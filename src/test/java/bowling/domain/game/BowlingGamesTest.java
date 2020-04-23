package bowling.domain.game;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGamesTest {
    private Players players;
    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        players = new Players(Arrays.asList(new Player("jjy"), new Player("ddd")));
        bowlingGames = new BowlingGames(players);
    }

    @Test
    @DisplayName("볼링게임 전체 끝 테스트")
    void isEndTest() {
        for (int i = 0; i < 24; i++) {
            bowlingGames.throwBall(Point.of(10));
        }
        assertThat(
                bowlingGames.isEnd()
        ).isTrue();
    }
}
