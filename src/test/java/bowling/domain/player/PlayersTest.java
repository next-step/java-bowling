package bowling.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        players = Players.of(Arrays.asList("이름1", "이름2", "이름3"));
    }

    @Test
    void 이름목록을입력받아_플레이어들을생성한다() {
        assertThat(players).isEqualTo(Players.of(Arrays.asList("이름1", "이름2", "이름3")));
    }
    @Test
    void 프레임완료되기전까진_처음차례() {
        assertThat(players.whoseTurn()).isEqualTo("이름1");
        players.play(3);
        assertThat(players.whoseTurn()).isEqualTo("이름1");
    }

    @Test
    void 프레임완료후_다음차례() {
        players.play(3);
        players.play(7);
        assertThat(players.whoseTurn()).isEqualTo("이름2");
    }

    @Test
    void 한바퀴돈후_차례() {
        players.play(10);
        players.play(10);
        players.play(10);
        assertThat(players.whoseTurn()).isEqualTo("이름1");
    }
}
