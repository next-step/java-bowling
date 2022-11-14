package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private static final int BONUS_ROUND = 2;

    @Test
    @DisplayName("모든 사용자의 게임이 끝나야 true를 반환해야합니다.")
    void shouldReturnWhetherGameFinish() {
        Players players = new Players(List.of(
                new Player(new Username("kcs"), new Bowling()),
                new Player(new Username("abc"), new Bowling())
        ));

        boolean resultA = players.isFinish();

        playUntilGameFinish(players, 2);

        assertThat(resultA).isFalse();
    }

    private void playUntilGameFinish(Players players, int userNumber) {
        IntStream.range(0, BowlingRound.LAST_ROUND_NUM + BONUS_ROUND)
                .forEach(player -> {
                    players.findPlayerByPosition(new Position(0)).bowl(10);
                    players.findPlayerByPosition(new Position(1)).bowl(10);
                });
    }

    @Test
    void shouldFindBowlingByUsername() {
        Player player = new Player(new Username("kcs"), new Bowling());
        Players players = new Players(List.of(player));

        Bowling foundBowling = players.findBowlingByUsername(new Username("kcs"));

        assertThat(player.getBowling()).isEqualTo(foundBowling);
    }

}
