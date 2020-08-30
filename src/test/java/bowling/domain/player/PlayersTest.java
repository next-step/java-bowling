package bowling.domain.player;

import bowling.domian.player.Player;
import bowling.domian.player.Players;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("전체 플레이어의 게임 종료 확인")
    public void checkGameEnd() {
        Player first = Player.get("aaa");
        Player second = Player.get("bbb");

        for (int i = 0 ; i < 11; i++) {
            first.bowl(10);
            second.bowl(10);
        }

        Players players = Players.of(Arrays.asList(first, second));

        assertThat(players.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 진행중 확인")
    public void checkGameNotEnd() {
        Player first = Player.get("aaa");
        Player second = Player.get("bbb");

        for (int i = 0 ; i < 10; i++) {
            first.bowl(10);
            second.bowl(10);
        }
        first.bowl(10);

        Players players = Players.of(Arrays.asList(first, second));

        assertThat(players.isGameEnd()).isFalse();
    }


    @Test
    @DisplayName("현재 프레임에서 투구 가능한 사용자만 출력")
    public void getPlayersWhoNeedBowl() {
        Player first = Player.get("aaa");
        Player second = Player.get("bbb");

        first.bowl(10);
        second.bowl(3);

        Players players = Players.of(Arrays.asList(first, second));

        assertThat(players.getCurrentPlayers()).hasSize(1);
    }
}
