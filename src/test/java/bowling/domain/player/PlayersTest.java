package bowling.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bowling.domain.frame.PlayerTest.allStrikePlayer;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;
    private List<String> nameList = Arrays.asList("이름1", "이름2", "이름3");

    @BeforeEach
    void setUp() {
        players = Players.of(nameList);
    }

    @Test
    void 이름목록을입력받아_플레이어들을생성한다() {
        assertThat(players).isEqualTo(Players.of(nameList));
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
        nameList.forEach(i -> players.play(10));
        assertThat(players.whoseTurn()).isEqualTo("이름1");
        assertThat(players.isFinished()).isFalse();
    }

    @Test
    void 모든플레이어의프레임이종료_여러사람우승() {
        for(int i = 0; i < nameList.size() * 12; i++) {
            players.play(10);
        }
        assertThat(players.isFinished()).isTrue();
        assertThat(players.getWinner())
                .containsExactly(allStrikePlayer("이름1"), allStrikePlayer("이름2"), allStrikePlayer("이름3"));
    }

    @Test
    void 모든플레이어프레임이종료_한사람만우승() {
        while(!players.isFinished()) {
            if(players.whoseTurn().equals("이름3")) {
                players.play(10);
                continue;
            }
            players.play(0);
        }
        assertThat(players.getWinner()).containsExactly(allStrikePlayer("이름3"));
    }
}
