package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(new Scoreboard(new Name("cys")));
        this.bowlingGame = new BowlingGame(scoreboards);
    }

    @DisplayName("볼링 게임 점수를 추가한다.")
    @Test
    void play() {
        Scoreboard expected = new Scoreboard(new Name("cys"));
        expected.addScore(Score.of(1), new Round(1));

        assertThat(this.bowlingGame.play(Score.of(1), 0).scoreboards().get(0)).isEqualTo(expected);
    }

    @DisplayName("게임이 끝났는지 boolean값을 반환한다.")
    @Test
    void isEnd() {
        for (int i = 1; i <= 10; i++) {
            this.bowlingGame.setNextRound();
        }

        assertThat(this.bowlingGame.isEnd()).isTrue();
    }

    @Test
    void round() {
        for (int i = 1; i < 10; i++) {
            this.bowlingGame.setNextRound();
        }
        assertThat(this.bowlingGame.round()).isEqualTo(10);
    }
}
