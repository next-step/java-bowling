package bowling;

import bowling.bowler.Bowler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private Bowler bowler1;
    private Bowler bowler2;
    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        bowler1 = Bowler.of("LHG");
        bowler2 = Bowler.of("PJS");
        List<Bowler> bowlers = Arrays.asList(bowler1, bowler2);
        bowlingGames = BowlingGames.start(bowlers);
    }

    @Test
    @DisplayName("게임 참가 인원 수 확인")
    void size() {
        assertThat(bowlingGames.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("LHG 의 볼링 진행 확인")
    void bowl() {
        bowlingGames.bowl(bowler1, "10");
        BowlingGame bowlingGame = bowlingGames.getBowlingGames().get(0);

        assertThat(bowlingGame.getBowlerName()).isEqualTo("LHG");
        assertThat(bowlingGame.getLastFrame().getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼링 종료")
    void bowlingEnd() {
        for (int i = 0; i < 12; i++) {
            bowlingGames.bowl(bowler1, "10");
            bowlingGames.bowl(bowler2, "10");
        }
        assertThat(bowlingGames.isEnd()).isTrue();
    }

    @Test
    @DisplayName("한명만 볼링이 끝날 경우 게임 종료되지 않음")
    void bowlingEndFalse() {
        for (int i = 0; i < 12; i++) {
            bowlingGames.bowl(bowler1, "10");
        }
        assertThat(bowlingGames.isEnd()).isFalse();
    }
}
