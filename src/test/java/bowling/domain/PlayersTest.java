package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("플레이들의 대한 테스트")
class PlayersTest {

    @DisplayName("같은 이름의 플레이어가 존재하는 경우 예외가 발생한다.")
    @Test
    void duplicate() {
        final List<Player> players = List.of(new Player("A"), new Player("A"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 플레이어가 게임이 끝난 경우 끝났다고 판단한다.")
    @Test
    void allEnd() {
        final Player 첫번째선수_게임끝 = createFinishedPlayer("A");
        final Player 두번째선수_게임끝 = createFinishedPlayer("B");
        final Player 세번째선수_게임끝 = createFinishedPlayer("C");
        final Players players = new Players(List.of(첫번째선수_게임끝, 두번째선수_게임끝, 세번째선수_게임끝));

        assertThat(players.canEnd()).isTrue();
    }

    @DisplayName("모든 플레이어 중 한명의 플레이가 게임이 끝나지 않은 경우 끝났다고 판단하지 않는다.")
    @Test
    void allNotEnd() {
        final Player 첫번째선수_게임끝 = createFinishedPlayer("A");
        final Player 두번째선수_게임끝 = createFinishedPlayer("B");
        final Player 세번째선수_게임진행중 = new Player("C");
        세번째선수_게임진행중.bowl(10);
        final Players players = new Players(List.of(첫번째선수_게임끝, 두번째선수_게임끝, 세번째선수_게임진행중));

        assertThat(players.canEnd()).isFalse();
    }

    private Player createFinishedPlayer(final String playerName) {

        final Player player = new Player(playerName);
        for (int i = 0; i < 9; i++) {
            player.bowl(10);
        }
        player.bowl(0);
        player.bowl(0);

        return player;
    }
}
