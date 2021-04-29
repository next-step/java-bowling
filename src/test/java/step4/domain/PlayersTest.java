package step4.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class PlayersTest {
    Players players;

    @BeforeEach
    void setUp() {
        players = new Players(Arrays.asList(new Player("KSB"), new Player("KBS")));
    }

    @Test
    @DisplayName("각 플레이어의 결과 점수 리스트를 반환한다.")
    public void scores() throws Exception {
        List<Scores> scores = players.scores();
        then(scores).hasSize(2);
    }

    @Test
    @DisplayName("각 플레이어의 이름 리스트를 반환한다.")
    public void names() throws Exception {
        List<Name> names = players.names();
        then(names).hasSize(2);
    }

    @Test
    @DisplayName("모든 플레이어가 게임을 완료했다면, 참을 반환한다.")
    public void isAllFinished() throws Exception {
        //given
        Player firstPlayer = players.players().get(0);
        Player secondPlayer = players.players().get(1);
        for (int i = 0; i < 12; i++) {
            firstPlayer.throwBowl("10");
            secondPlayer.throwBowl("10");
        }

        //when
        boolean isAllFinished = players.isAllFinished();

        then(isAllFinished).isTrue();
    }
}
