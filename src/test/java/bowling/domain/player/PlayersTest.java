package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @DisplayName("이름 문자열 리스트로 플레이어들을 생성한다")
    @Test
    void of() {
        assertThat(players()).isEqualTo(new Players(List.of(player("PJS"), player("KJP"))));
    }

    @DisplayName("모든 플레이어가 끝났다면 참을 반환한다")
    @Test
    void isAllFinished_true() {
        Players players = new Players(List.of(
                player("PJS", finishedFrames()),
                player("KJP", finishedFrames())));

        assertThat(players.isAllFinished()).isTrue();
    }

    @DisplayName("모든 플레이어가 끝나지 않았다면 거짓을 반환한다")
    @Test
    void isAllFinished_false() {
        assertThat(players().isAllFinished()).isFalse();
    }

    private Players players() {
        return Players.of(List.of("PJS", "KJP"));
    }

    private Player player(String playerName) {
        return new Player(new PlayerName(playerName));
    }

    private Player player(String playerName, Frames frames) {
        return new Player(new PlayerName(playerName), frames);
    }

    private Frames finishedFrames() {
        Frames result = Frames.init();
        for (int i = 0; i < 12; i++) {
            result = result.bowl(FallenPin.of(10));
        }
        return result;
    }
}
