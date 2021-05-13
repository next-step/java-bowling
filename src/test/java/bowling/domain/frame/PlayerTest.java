package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Score;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private static Player player;

    @BeforeEach
    void setUp() {
        player = Player.of("테스트");
    }

    @Test
    void 플레이어생성() {
        assertThat(player).isEqualTo(Player.of("테스트"));
        assertThat(player.getLastIndex()).isEqualTo(1);
        assertThat(player.isFinished()).isFalse();
    }

    @Test
    void 프레임이종료되었을때_다음프레임생성() {
        assertThat(player.playWithNext(HitNumber.of(10))).isTrue();
        assertThat(player.getLastIndex()).isEqualTo(2);
    }

    @Test
    void 프레임이아직종료되지않았을때_현재프레임() {
        assertThat(player.playWithNext(HitNumber.of(3))).isFalse();
        assertThat(player.getLastIndex()).isEqualTo(1);
    }

    @Test
    void 마지막프레임까지_올스트라이크() {
        Player lastFrames = allStrikePlayer("ALL");
        assertThat(lastFrames.isFinished()).isTrue();
        List<Score> scores = lastFrames.totalScores();
        assertThat(scores.get(scores.size() - 1)).isEqualTo(Score.of(300));
    }

    public static Player allStrikePlayer(String name) {
        Player player = Player.of(name);
        for (int i = 0; i < 12; i++) {
            player.playWithNext(HitNumber.of(10));
        }
        return player;
    }
}
