package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("플레이어 일급 컬렉션 테스트")
class PlayersTest {

    @DisplayName("같은 이름의 플레이어가 존재하는 경우 예외가 발생한다.")
    @Test
    void duplicate() {
        List<Player> players = List.of(new Player("A"), new Player("A"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 플레이어가 게임이 끝난 경우 끝났다고 판단한다.")
    @Test
    void allEnd() {
        Player playerA = getFinishedPlayerFixture("A");
        Player playerB = getFinishedPlayerFixture("B");
        Player playerC = getFinishedPlayerFixture("C");
        Players players = new Players(List.of(playerA, playerB, playerC));

        assertThat(players.isAllEnd()).isTrue();
    }

    @DisplayName("모든 플레이어 중 한명의 플레이가 게임이 끝나지 않은 경우 끝났다고 판단하지 않는다.")
    @Test
    void allNotEnd() {
        Player playerA = getFinishedPlayerFixture("A");
        Player playerB = getFinishedPlayerFixture("B");
        Player playerC = new Player("C");
        playerC.bowl(10);
        Players players = new Players(List.of(playerA, playerB, playerC));

        assertThat(players.isAllEnd()).isFalse();
    }

    private Player getFinishedPlayerFixture(String playerName) {
        Player player = new Player(playerName);
        for (int i = 0; i < 9; i++) {
            player.bowl(10);
        }
        player.bowl(0);
        player.bowl(0);

        return player;
    }
}
