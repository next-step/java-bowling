package bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.model.DownPin.DOWN_ALL;
import static bowling.model.Player.of;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private List<Player> players = Arrays.asList(of("A"), of("B"), of("C"));
    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        bowlingGames = BowlingGames.settingOf(players);
    }

    @DisplayName("여러명의 플레이어를 등록한다")
    @Test
    void settingBowlingGame() {
        // when
        List<Board> boards = bowlingGames.results();

        // then
        assertThat(boards).hasSize(players.size());
    }

    @DisplayName("현재 턴의 플레이어 정보를 반환한다")
    @Test
    void getCurrentPlayer() {
        // when
        BowlingGame currentGame = bowlingGames.getCurrentGame();

        // then
        assertThat(currentGame.getPlayer()).isEqualTo(players.get(0));
    }

    @DisplayName("첫번째 플레이어 턴이 끝난 후 두번째 플레이어 정보를 반환한다")
    @Test
    void getCurrentPlayer_strike() {
        // given
        bowlingGames.getCurrentGame().play(DOWN_ALL);

        BowlingGame currentGame = bowlingGames.getCurrentGame();

        // then
        assertThat(currentGame.getPlayer()).isEqualTo(players.get(1));
    }

    @DisplayName("게임이 끝나지 않았을 시 게임을 종료하지 않는")
    @Test
    void isGameOver_false() {
        // then
        assertThat(bowlingGames.isGameOver()).isFalse();
    }

    @DisplayName("모든 유저가 게임을 완료했을 시 게임을 종료한다")
    @Test
    void isGameOver_true() {
        // when
        IntStream.rangeClosed(1, 12 * players.size())
                .forEach(ignore -> {
                    BowlingGame currentPlayer = bowlingGames.getCurrentGame();
                    currentPlayer.play(DOWN_ALL);
                });

        // then
        assertThat(bowlingGames.isGameOver()).isTrue();
    }
}