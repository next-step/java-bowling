package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @DisplayName("볼링 게임 점수를 추가한다.")
    @Test
    void play() {
        BowlingGame bowlingGame = new BowlingGame(new Scoreboard(new Name("cys")));
        Scoreboard expected = new Scoreboard(new Name("cys"));
        expected.addScore(Score.of(1), new Round(1));

        assertThat(bowlingGame.play(Score.of(1))).isEqualTo(expected);
    }

    @DisplayName("게임이 끝났는지 boolean값을 반환한다.")
    @Test
    void isEnd() {
        Scoreboard scoreboard = new Scoreboard(new Name("cys"));
        BowlingGame bowlingGame = new BowlingGame(scoreboard);
        for (int i = 1; i < 10; i++) {
            bowlingGame.play(Score.of(1));
            bowlingGame.play(Score.of(1));
            bowlingGame.isEnd();
        }
        bowlingGame.play(Score.of(10));
        bowlingGame.play(Score.of(10));

        assertThat(bowlingGame.isEnd()).isFalse();

        bowlingGame.play(Score.of(1));

        assertThat(bowlingGame.isEnd()).isTrue();
    }

    @Test
    void round() {
        Scoreboard scoreboard = new Scoreboard(new Name("cys"));
        BowlingGame bowlingGame = new BowlingGame(scoreboard);
        for (int i = 1; i < 10; i++) {
            assertThat(bowlingGame.round()).isEqualTo(i);
            bowlingGame.play(Score.of(1));
            bowlingGame.play(Score.of(1));
            bowlingGame.isEnd();
        }
        assertThat(bowlingGame.round()).isEqualTo(10);
    }
}
